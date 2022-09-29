package exploreWithMe.service.registered.impl;

import exploreWithMe.map.EventMapper;
import exploreWithMe.map.RequestMapper;
import exploreWithMe.model.event.*;
import exploreWithMe.model.request.Request;
import exploreWithMe.model.request.RequestDto;
import exploreWithMe.model.request.RequestState;
import exploreWithMe.page.PageLimit;
import exploreWithMe.repository.EventRepository;
import exploreWithMe.repository.RequestRepository;
import exploreWithMe.repository.UserRepository;
import exploreWithMe.service.registered.UserEventService;
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
    public EventDto updateEventByUserId(Long userId, EventUpdateDto eventUpdateDto) {
        Event event = eventRepository.findById(eventUpdateDto.getEventId()).orElseThrow();
        eventMapper.mapToEvent(eventUpdateDto, event);
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
        List<Request> requests = requestRepository.findRequestsByRequesterId(userId);
        return requests.stream()
                .map(requestMapper::mapToRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto confirmRequest(Long userId, Long eventId, Long reqId) {
        Request request = requestRepository.findRequestByUserId(reqId, eventId, userId);
        request.setStatus(RequestState.CONFIRMED);
        requestRepository.saveAndFlush(request);
        return toRequestDto(request);
    }

    @Override
    public RequestDto rejectRequest(Long userId, Long eventId, Long reqId) {
        Request request = requestRepository.findRequestByUserId(reqId, eventId, userId);
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
