package exploreWithMeServer.service.all.impl;

import exploreWithMeServer.controller.all.EventClient;
import exploreWithMeServer.map.EventMapper;
import exploreWithMeServer.model.event.Event;
import exploreWithMeServer.model.event.EventDto;
import exploreWithMeServer.model.event.EventShortDto;
import exploreWithMeServer.page.PageLimit;
import exploreWithMeServer.repository.EventRepository;
import exploreWithMeServer.service.all.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    private final EventClient eventClient;

    private final EventMapper mapper;

    @Override
    public List<EventShortDto> searchEvents(String text,
                                            List<Long> categories,
                                            Boolean paid,
                                            LocalDateTime rangeStart,
                                            LocalDateTime rangeEnd,
                                            Boolean onlyAvailable,
                                            String sort,
                                            Integer from,
                                            Integer size,
                                            HttpServletRequest request) {
        eventClient.addViews(request);
        List<Event> events;
        if (onlyAvailable) {
            events = repository.searchEventsOnlyAvailable(
                    text,
                    categories,
                    paid,
                    rangeStart,
                    rangeEnd,
                    sort,
                    PageLimit.of(from, size)).getContent();

        } else {
            events = repository.searchEvents(text,
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
    public EventDto getEventById(Long id, HttpServletRequest request) {
        eventClient.addViews(request);
        Event event = repository.findById(id).orElseThrow();
        return mapper.mapToEventDto(event);
    }


}
