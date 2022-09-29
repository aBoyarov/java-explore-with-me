package exploreWithMe.service.admin;

import exploreWithMe.model.compilation.Compilation;
import exploreWithMe.model.compilation.CompilationDto;
import exploreWithMe.model.compilation.NewCompilationDto;

/**
 * @author Andrey Boyarov
 */
public interface AdminCompilationService {

    CompilationDto create(NewCompilationDto newCompilationDto);

    void deleteById(Long compId);

    void deleteEventFromCompilation(Long compId, Long eventId);

    Compilation addEventToCompilation(Long compId, Long eventId);

    void deleteCompilationFromMainPage(Long compId);

    void pinCompilationOnMainPage(Long compId);
}
