package exploreWithMe.controller.all;

import exploreWithMe.model.event.Event;
import exploreWithMe.model.event.EventDto;
import exploreWithMe.model.event.EventShortDto;
import exploreWithMe.service.all.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/events")
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


//    @GetMapping
//    public List<EventShortDto> getEvents(@RequestParam String text,
//                                         @RequestParam List<Long> categories,
//                                         @RequestParam Boolean paid,
//                                         @RequestParam LocalDateTime rangeStart,
//                                         @RequestParam LocalDateTime rangeEnd,
//                                         @RequestParam Boolean onlyAvailable,
//                                         @RequestParam String sort,
//                                         @RequestParam(required = false, defaultValue = "0") Integer from,
//                                         @RequestParam(required = false, defaultValue = "1") Integer size){
//        return eventService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
//    }
//
//    @GetMapping("/{id}")
//    public EventDto getEventById(@PathVariable Long id){
//        return eventService.getEventById(id);
//    }
}
