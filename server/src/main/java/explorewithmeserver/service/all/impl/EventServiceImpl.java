package explorewithmeserver.service.all.impl;

import explorewithmeserver.client.EventClient;
import explorewithmeserver.map.EventMapper;
import explorewithmeserver.model.event.Event;
import explorewithmeserver.model.event.EventDto;
import explorewithmeserver.model.event.EventShortDto;
import explorewithmeserver.model.event.EventSort;
import explorewithmeserver.repository.CriteriaRepository;
import explorewithmeserver.repository.EventRepository;
import explorewithmeserver.service.all.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    private final CriteriaRepository criteriaRepository;

    @Override
    public List<EventShortDto> searchEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                            LocalDateTime rangeEnd, Boolean onlyAvailable, String sort, Integer from,
                                            Integer size, HttpServletRequest request) {
        return criteriaRepository.searchEventsOnlyAvailable(
                text, categories, paid, rangeStart, rangeEnd, from, size).stream()
                .map(mapper::mapToEventShortDto)
                .collect(Collectors.toList());

    }


    @Override
    public EventDto getEventById(Long id, HttpServletRequest request) {
        Event event = repository.findById(id).orElseThrow();
        EventDto eventDto = mapper.mapToEventDto(event);
        eventClient.addViews(request);
        return eventDto;
    }


}
