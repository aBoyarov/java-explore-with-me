package exploreWithMe.service.registered.impl;

import exploreWithMe.model.event.*;
import exploreWithMe.model.request.Request;
import exploreWithMe.model.request.RequestDto;
import exploreWithMe.model.request.RequestState;
import exploreWithMe.page.PageLimit;
import exploreWithMe.repository.EventRepository;
import exploreWithMe.repository.RequestRepository;
import exploreWithMe.service.registered.UserEventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final ModelMapper modelMapper;

    @Override
    public List<EventShortDto> getEventsByUserId(Long userId, Integer from, Integer size) {
        List<Event> events = eventRepository.findEventsByInitiatorId(userId, PageLimit.of(from, size)).getContent();
        return events.stream()
                .map(this::toEventShortDto)
                .collect(Collectors.toList());

    }

    @Override
    public EventDto updateEventByUserId(Long userId, EventUpdateDto eventUpdateDto) {
        Event event = eventRepository.saveAndFlush(toEventFromUpdateDto(eventUpdateDto));
        return mapToEventDto(event);
    }

    @Override
    public NewEventDto createEvent(Long userId, NewEventDto eventDto) {
        eventRepository.saveAndFlush(mapToEvent(eventDto));
        return eventDto;
    }

    @Override
    public EventDto getEventById(Long userId, Long eventId) {
        Event event = eventRepository.findEventByIdAndInitiatorId(eventId, userId);
        return mapToEventDto(event);
    }

    @Override
    public EventDto eventCancel(Long userId, Long eventId) {
        Event event = eventRepository.findEventByIdAndInitiatorId(eventId, userId);
        if(event.getState().equals(State.PENDING)) {
            event.setState(State.CANCELED);
            eventRepository.saveAndFlush(event);
        }
        return mapToEventDto(event);
    }

    @Override
    public List<RequestDto> getRequestsByUserId(Long userId, Long eventId) {
        List<Request> requests = requestRepository.findRequestsByUserId(eventId, userId);
        return requests.stream()
                .map(this::toRequestDto)
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



    private EventDto mapToEventDto(Event event){
        return modelMapper.map(event, EventDto.class);
    }

    Converter<String, LocalDateTime> convert = src -> src
            .getSource() == null ? null : mapToDate(src.getSource());

    private Event mapToEvent(NewEventDto eventDto){
        modelMapper.createTypeMap(NewEventDto.class, Event.class)
                .addMappings(m -> m.using(convert)
                        .map(NewEventDto::getEventDate, Event::setEventDate));
        return modelMapper.map(eventDto, Event.class);
    }

    private LocalDateTime mapToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    private EventShortDto toEventShortDto (Event event){
        return modelMapper.map(event, EventShortDto.class);
    }

    private Event toEventFromUpdateDto(EventUpdateDto eventUpdateDto){
        return modelMapper.map(eventUpdateDto, Event.class);
    }

    private RequestDto toRequestDto (Request request){
        return modelMapper.map(request, RequestDto.class);
    }
}
