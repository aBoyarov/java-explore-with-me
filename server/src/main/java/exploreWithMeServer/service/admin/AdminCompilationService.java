package exploreWithMeServer.service.admin;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.model.compilation.CompilationDto;
import exploreWithMeServer.model.compilation.NewCompilationDto;

/**
 * @author Andrey Boyarov
 */
public interface AdminCompilationService {

    CompilationDto create(NewCompilationDto newCompilationDto);

    void deleteById(Long compId) throws NotFoundException;

    void deleteEventFromCompilation(Long compId, Long eventId) throws NotFoundException;

    CompilationDto addEventToCompilation(Long compId, Long eventId) throws NotFoundException;

    void deleteCompilationFromMainPage(Long compId) throws NotFoundException;

    void pinCompilationOnMainPage(Long compId) throws NotFoundException;
}
