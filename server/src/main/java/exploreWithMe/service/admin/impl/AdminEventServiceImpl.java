package exploreWithMe.service.admin.impl;

import exploreWithMe.model.event.*;
import exploreWithMe.model.request.Request;
import exploreWithMe.model.request.RequestDto;
import exploreWithMe.page.PageLimit;
import exploreWithMe.repository.EventRepository;
import exploreWithMe.service.admin.AdminEventService;
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
public class AdminEventServiceImpl implements AdminEventService {

    private final EventRepository repository;

    private final ModelMapper mapper;


    @Override
    public List<EventDto> getEvents(List<Long> users,
                                    List<String> states,
                                    List<Long> categories,
                                    LocalDateTime rangeStart,
                                    LocalDateTime rangeEnd,
                                    Integer from,
                                    Integer size) {
        return repository.getAllEvents(users,
                        states,
                        categories,
                        rangeStart,
                        rangeEnd,
                        PageLimit.of(from, size)).getContent().stream()
                .map(event -> mapper.map(event, EventDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NewEventDto updateEvent(Long eventId, NewEventDto newEventDto) {
        repository.saveAndFlush(mapToEvent(newEventDto));
        return newEventDto;
    }

    @Override
    public Event publishEvent(Long eventId) {
        Event event = repository.findById(eventId).orElseThrow();
        event.setState(State.PUBLISHED);
        return repository.saveAndFlush(event);
    }

    @Override
    public Event rejectEvent(Long eventId) {
        Event event = repository.findById(eventId).orElseThrow();
        event.setState(State.CANCELED);
        return repository.saveAndFlush(event);
    }

    Converter<String, LocalDateTime> convert = src -> src
            .getSource() == null ? null : mapToDate(src.getSource());

    private Event mapToEvent(NewEventDto eventDto) {
        mapper.createTypeMap(NewEventDto.class, Event.class)
                .addMappings(m -> m.using(convert)
                        .map(NewEventDto::getEventDate, Event::setEventDate));
        return mapper.map(eventDto, Event.class);
    }

    private LocalDateTime mapToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    private EventShortDto toEventShortDto(Event event) {
        return mapper.map(event, EventShortDto.class);
    }

    private Event toEventFromUpdateDto(EventUpdateDto eventUpdateDto) {
        return mapper.map(eventUpdateDto, Event.class);
    }

    private RequestDto toRequestDto(Request request) {
        return mapper.map(request, RequestDto.class);
    }
}
