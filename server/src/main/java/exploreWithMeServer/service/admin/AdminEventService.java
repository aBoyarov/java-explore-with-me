package exploreWithMeServer.service.admin;

import exploreWithMeServer.exception.ForbiddenException;
import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.model.event.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface AdminEventService {

    List<EventDto> getEvents(List<Long> users,
                             List<State> states,
                             List<Long> categories,
                             LocalDateTime rangeStart,
                             LocalDateTime rangeEnd,
                             Integer from,
                             Integer size);

    EventDto updateEvent(Long eventId, EventUpdateDto eventUpdateDto) throws NotFoundException;

    EventDto publishEvent(Long eventId) throws NotFoundException, ForbiddenException;

    EventDto rejectEvent(Long eventId) throws NotFoundException, ForbiddenException;
}
