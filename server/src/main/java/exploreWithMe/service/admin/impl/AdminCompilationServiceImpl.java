package exploreWithMe.service.admin.impl;

import exploreWithMe.model.compilation.Compilation;
import exploreWithMe.service.admin.AdminCompilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {
    @Override
    public Compilation create(Compilation compilation) {
        return null;
    }

    @Override
    public void deleteById(Long compId) {

    }

    @Override
    public void deleteEventFromCompilation(Long compId, Long eventId) {

    }

    @Override
    public Compilation addEventToCompilation(Long compId, Long eventId) {
        return null;
    }

    @Override
    public void deleteCompilationFromMainPage(Long compId) {

    }

    @Override
    public void pinCompilationOnMainPage(Long compId) {

    }
}
