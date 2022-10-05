package exploreWithMeServer.service.registered;

import exploreWithMeServer.model.request.RequestDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface UserRequestService {
    List<RequestDto> getRequests(Long userId);

    RequestDto createRequest(Long userId, Long eventId);

    RequestDto cancelRequest(Long userId, Long requestId);
}
