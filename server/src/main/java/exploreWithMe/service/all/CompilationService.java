package exploreWithMe.service.all;

import exploreWithMe.model.compilation.Compilation;
import exploreWithMe.model.compilation.CompilationDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface CompilationService {
    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto getCompilationById(Long compId);
}
