package explorewithmeserver.service.registered;

import explorewithmeserver.exception.ForbiddenException;
import explorewithmeserver.exception.NotFoundException;
import explorewithmeserver.model.comment.CommentDto;
import explorewithmeserver.model.comment.NewCommentDto;
import explorewithmeserver.model.event.*;
import explorewithmeserver.model.request.RequestDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface UserEventService {
    List<EventShortDto> getEventsByUserId(Long userId, Integer from, Integer size);

    EventDto updateEventByUserId(Long userId, EventUpdateDto event) throws NotFoundException, ForbiddenException;

    EventDto createEvent(Long userId, NewEventDto event);

    EventDto getEventById(Long userId, Long eventId);

    EventDto eventCancel(Long userId, Long eventId);

    List<RequestDto> getRequestsByUserId(Long userId, Long eventId);

    RequestDto confirmRequest(Long userId, Long eventId, Long reqId);

    RequestDto rejectRequest(Long userId, Long eventId, Long reqId);

    CommentDto addComment(Long userId, Long eventId, NewCommentDto newCommentDto) throws NotFoundException;

    void deleteComment(Long userId, Long eventId, Long commentId) throws NotFoundException;
}
