package exploreWithMe.service.admin;

import exploreWithMe.model.event.Event;
import exploreWithMe.model.event.EventDto;
import exploreWithMe.model.event.NewEventDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface AdminEventService {

    List<Event> getEvents(List<Long> users,
                             List<String> states,
                             List<Long> categories,
                             LocalDateTime rangeStart,
                             LocalDateTime rangeEnd,
                             Integer from,
                             Integer size);

    NewEventDto updateEvent(Long eventId, NewEventDto newEventDto);

    Event publishEvent(Long eventId);

    Event rejectEvent(Long eventId);
}
