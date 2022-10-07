package explorewithmeserver.controller.registered;

import explorewithmeserver.model.request.RequestDto;
import explorewithmeserver.service.registered.UserRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
@RestController
@RequestMapping("/users/{userId}/requests")
@Slf4j
@RequiredArgsConstructor
public class UserRequestController {

    private final UserRequestService userRequestService;

    @GetMapping
    public List<RequestDto> getRequests(@PathVariable Long userId) {
        return userRequestService.getRequests(userId);
    }

    @PostMapping
    public RequestDto createRequest(@PathVariable Long userId,
                                    @RequestParam Long eventId) {
        return userRequestService.createRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public RequestDto cancelRequest(@PathVariable Long userId,
                                    @PathVariable Long requestId) {
        return userRequestService.cancelRequest(userId, requestId);
    }
}
