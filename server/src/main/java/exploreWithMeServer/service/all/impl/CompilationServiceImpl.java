package exploreWithMeServer.service.all.impl;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.map.CompilationMapper;
import exploreWithMeServer.model.compilation.Compilation;
import exploreWithMeServer.model.compilation.CompilationDto;
import exploreWithMeServer.page.PageLimit;
import exploreWithMeServer.repository.CompilationRepository;
import exploreWithMeServer.service.all.CompilationService;
import exploreWithMeServer.valid.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService{

    private final CompilationRepository repository;

    private final Validator validator;
    private final CompilationMapper mapper;
    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size) {
        List<Compilation> compilations = repository
                .findCompilationsByPinnedIsOrderByIdAsc(pinned, PageLimit.of(from, size)).getContent();
        return compilations.stream()
                .map(mapper::mapToCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationById(Long compId) throws NotFoundException {
        Compilation compilation = validator.validCompilation(compId);
        return mapper.mapToCompilationDto(compilation);
    }


}
