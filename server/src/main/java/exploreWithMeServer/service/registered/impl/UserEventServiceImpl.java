package exploreWithMeServer.service.registered.impl;

import exploreWithMeServer.exception.ForbiddenException;
import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.map.EventMapper;
import exploreWithMeServer.map.RequestMapper;
import exploreWithMeServer.model.event.*;
import exploreWithMeServer.model.request.Request;
import exploreWithMeServer.model.request.RequestDto;
import exploreWithMeServer.model.request.RequestState;
import exploreWithMeServer.page.PageLimit;
import exploreWithMeServer.repository.EventRepository;
import exploreWithMeServer.repository.RequestRepository;
import exploreWithMeServer.repository.UserRepository;
import exploreWithMeServer.service.registered.UserEventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class UserEventServiceImpl implements UserEventService {

    private final EventRepository eventRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;
    private final ModelMapper modelMapper;

    private final RequestMapper requestMapper;

    @Override
    public List<EventShortDto> getEventsByUserId(Long userId, Integer from, Integer size) {
        return eventRepository.findEventsByInitiatorId(userId, PageLimit.of(from, size)).getContent().stream()
                .map(eventMapper::mapToEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto updateEventByUserId(Long userId, EventUpdateDto eventUpdateDto)
            throws ForbiddenException, NotFoundException {

        Event event = eventRepository.findById(eventUpdateDto.getEventId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Event with id=%d was not found.", eventUpdateDto.getEventId())));

        if (event.getState().equals(State.PUBLISHED)) {
            throw new ForbiddenException("Only pending or canceled events can be changed");
        }

        eventMapper.mapToEvent(eventUpdateDto, event);

        if(event.getState().equals(State.CANCELED)){
            event.setState(State.PENDING);
        }

        eventRepository.save(event);
        return eventMapper.mapToEventDto(event);
    }

    @Override
    public EventDto createEvent(Long userId, NewEventDto newEventDto) {
        Event event = eventMapper.mapToEvent(newEventDto);
        event.setInitiator(userRepository.findById(userId).orElseThrow());
        event.setState(State.PENDING);
        eventRepository.save(event);
        return eventMapper.mapToEventDto(event);
    }

    @Override
    public EventDto getEventById(Long userId, Long eventId) {
        Event event = eventRepository.findEventByIdAndInitiatorId(eventId, userId);
        return eventMapper.mapToEventDto(event);
    }

    @Override
    public EventDto eventCancel(Long userId, Long eventId) {
        Event event = eventRepository.findEventByIdAndInitiatorId(eventId, userId);
        if (event.getState().equals(State.PENDING)) {
            event.setState(State.CANCELED);
            eventRepository.save(event);
        }
        return eventMapper.mapToEventDto(event);
    }

    @Override
    public List<RequestDto> getRequestsByUserId(Long userId, Long eventId) {
        List<Request> requests = requestRepository.findRequestsByRequesterIdAndEventId(userId, eventId);
        return requests.stream()
                .map(requestMapper::mapToRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto confirmRequest(Long userId, Long eventId, Long reqId) {
        Request request = requestRepository.findRequestByUserIdAndEventIdAndReqId(userId, eventId, reqId);
        request.setStatus(RequestState.CONFIRMED);
        requestRepository.saveAndFlush(request);
        return toRequestDto(request);
    }

    @Override
    public RequestDto rejectRequest(Long userId, Long eventId, Long reqId) {
        Request request = requestRepository.findRequestByUserId(reqId);
        request.setStatus(RequestState.REJECTED);
        requestRepository.saveAndFlush(request);
        return toRequestDto(request);
    }


    private Event toEventFromUpdateDto(EventUpdateDto eventUpdateDto) {
        return modelMapper.map(eventUpdateDto, Event.class);
    }

    private RequestDto toRequestDto(Request request) {
        return modelMapper.map(request, RequestDto.class);
    }
}
