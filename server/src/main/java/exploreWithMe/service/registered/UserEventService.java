package exploreWithMe.service.registered;

import exploreWithMe.model.event.*;
import exploreWithMe.model.request.Request;
import exploreWithMe.model.request.RequestDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface UserEventService {
    List<EventShortDto> getEventsByUserId(Long userId, Integer from, Integer size);

    EventDto updateEventByUserId(Long userId, EventUpdateDto event);

    NewEventDto createEvent(Long userId, NewEventDto event);

    EventDto getEventById(Long userId, Long eventId);

    EventDto eventCancel(Long userId, Long eventId);

    List<RequestDto> getRequestsByUserId(Long userId, Long eventId);

    RequestDto confirmRequest(Long userId, Long eventId, Long reqId);

    RequestDto rejectRequest(Long userId, Long eventId, Long reqId);
}
