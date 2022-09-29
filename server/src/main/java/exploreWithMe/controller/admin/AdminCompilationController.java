package exploreWithMe.controller.admin;

import exploreWithMe.model.compilation.Compilation;
import exploreWithMe.model.compilation.CompilationDto;
import exploreWithMe.model.compilation.NewCompilationDto;
import exploreWithMe.service.admin.AdminCompilationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/admin/compilations")
@Slf4j
@RequiredArgsConstructor
public class AdminCompilationController {

    private final AdminCompilationService adminCompilationService;

    @PostMapping
    public CompilationDto create(@RequestBody NewCompilationDto newCompilationDto){
        return adminCompilationService.create(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    public void deleteById(@PathVariable Long compId){
        adminCompilationService.deleteById(compId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable Long compId,
                                           @PathVariable Long eventId){
        adminCompilationService.deleteEventFromCompilation(compId, eventId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public Compilation addEventToCompilation(@PathVariable Long compId,
                                             @PathVariable Long eventId){
        return adminCompilationService.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping("/{compId}/pin")
    public void deleteCompilationFromMainPage(@PathVariable Long compId){
        adminCompilationService.deleteCompilationFromMainPage(compId);
    }

    @PatchMapping("/{compId}/pin")
    public void pinCompilationOnMainPage(@PathVariable Long compId){
        adminCompilationService.pinCompilationOnMainPage(compId);
    }
}
