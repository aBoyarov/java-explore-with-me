package exploreWithMe.controller.admin;

import exploreWithMe.model.event.Event;
import exploreWithMe.model.event.EventDto;
import exploreWithMe.model.event.NewEventDto;
import exploreWithMe.service.admin.AdminEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/admin/events")
@Slf4j
@RequiredArgsConstructor
public class AdminEventController {

    private final AdminEventService adminEventService;

    @GetMapping
    public List<Event> getEvents(@RequestParam(value = "users") List<Long> users,
                                 @RequestParam(value = "states") List<String> states,
                                 @RequestParam(value = "categories") List<Long> categories,
                                 @RequestParam LocalDateTime rangeStart,
                                 @RequestParam LocalDateTime rangeEnd,
                                 @RequestParam Integer from,
                                 @RequestParam Integer size) {
        return adminEventService.getEvents(users, states, categories, rangeStart, rangeEnd, from,size);
    }

    @PutMapping("/{eventId}")
    public NewEventDto updateEvent(@PathVariable Long eventId,
                                   @RequestBody NewEventDto newEventDto) {
        return adminEventService.updateEvent(eventId, newEventDto);
    }

    @PatchMapping("/{eventId}/publish")
    public Event publishEvent(@PathVariable Long eventId) {
        return adminEventService.publishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public Event rejectEvent(@PathVariable Long eventId) {
        return null;
    }
}
