package exploreWithMeServer.service.all;

import exploreWithMeServer.model.compilation.CompilationDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface CompilationService {
    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto getCompilationById(Long compId);
}
