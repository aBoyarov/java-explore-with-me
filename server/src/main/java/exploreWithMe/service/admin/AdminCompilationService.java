package exploreWithMe.service.admin;

import exploreWithMe.model.compilation.Compilation;

/**
 * @author Andrey Boyarov
 */
public interface AdminCompilationService {

    Compilation create(Compilation compilation);

    void deleteById(Long compId);

    void deleteEventFromCompilation(Long compId, Long eventId);

    Compilation addEventToCompilation(Long compId, Long eventId);

    void deleteCompilationFromMainPage(Long compId);

    void pinCompilationOnMainPage(Long compId);
}
