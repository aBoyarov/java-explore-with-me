package exploreWithMeServer.service.admin.impl;

import exploreWithMeServer.exception.ForbiddenException;
import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.map.EventMapper;
import exploreWithMeServer.model.event.*;
import exploreWithMeServer.page.PageLimit;
import exploreWithMeServer.repository.EventRepository;
import exploreWithMeServer.service.admin.AdminEventService;
import exploreWithMeServer.valid.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

    private final EventRepository repository;
    private final EventMapper mapper;
    private final Validator validator;


    @Override
    public List<EventDto> getEvents(List<Long> users,
                                    List<State> states,
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
                .map(mapper::mapToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto updateEvent(Long eventId, EventUpdateDto eventUpdateDto) throws NotFoundException {
        Event event = validator.validEvent(eventId);
        mapper.mapToEvent(eventUpdateDto, event);
        repository.save(event);
        return mapper.mapToEventDto(event);
    }

    @Override
    public EventDto publishEvent(Long eventId) throws NotFoundException, ForbiddenException {
        Event event = validator.validEvent(eventId);
        LocalDateTime publish = LocalDateTime.now();
        if (publish.plusHours(1L).isAfter(event.getEventDate())) {
            throw new ForbiddenException("Start date event must be no earlier than one hour from date publication.");
        }
        if (!event.getState().equals(State.PENDING)) {
            throw new ForbiddenException("Event must be pending.");
        }
        event.setPublishedOn(publish);
        event.setState(State.PUBLISHED);
        repository.save(event);
        return mapper.mapToEventDto(event);
    }

    @Override
    public EventDto rejectEvent(Long eventId) throws NotFoundException, ForbiddenException {
        Event event = validator.validEvent(eventId);
        if (event.getState().equals(State.PUBLISHED)) {
            throw new ForbiddenException("Event must not be published.");
        }
        event.setState(State.CANCELED);
        repository.save(event);
        return mapper.mapToEventDto(event);
    }
}
