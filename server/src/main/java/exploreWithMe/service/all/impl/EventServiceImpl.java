package exploreWithMe.service.all.impl;

import exploreWithMe.map.EventMapper;
import exploreWithMe.model.event.Event;
import exploreWithMe.model.event.EventDto;
import exploreWithMe.model.event.EventShortDto;
import exploreWithMe.page.PageLimit;
import exploreWithMe.repository.EventRepository;
import exploreWithMe.service.all.EventService;
import lombok.RequiredArgsConstructor;
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
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    private final EventMapper mapper;

    @Override
    public List<EventShortDto> getEvents(String text,
                                         List<Long> categories,
                                         Boolean paid,
                                         LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd,
                                         Boolean onlyAvailable,
                                         String sort,
                                         Integer from,
                                         Integer size) {
        List<Event> events;
        if (onlyAvailable) {
            events = repository.getEventsOnlyAvailable(
                    text,
                    categories,
                    paid,
                    rangeStart,
                    rangeEnd,
                    sort,
                    PageLimit.of(from, size)).getContent();

        } else {
            events = repository.getEvents(text,
                    categories,
                    paid,
                    rangeStart,
                    rangeEnd,
                    sort,
                    PageLimit.of(from, size)).getContent();
        }
        return events.stream()
                .map(mapper::mapToEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto getEventById(Long id) {
        Event event = repository.findById(id).orElseThrow();
        return mapper.mapToEventDto(event);
    }

    private LocalDateTime format(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
}
