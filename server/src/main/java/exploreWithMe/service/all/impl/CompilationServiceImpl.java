package exploreWithMe.service.all.impl;

import exploreWithMe.model.compilation.Compilation;
import exploreWithMe.model.compilation.CompilationDto;
import exploreWithMe.page.PageLimit;
import exploreWithMe.repository.CompilationRepository;
import exploreWithMe.service.all.CompilationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper mapper;
    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size) {
        List<Compilation> compilations = repository
                .findCompilationsByPinnedIsOrderByIdAsc(pinned, PageLimit.of(from, size)).getContent();
        return compilations.stream()
                .map(this::toCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        Compilation compilation = repository.findById(compId).orElseThrow();
        return toCompilationDto(compilation);
    }

    private CompilationDto toCompilationDto(Compilation compilation){
        return mapper.map(compilation, CompilationDto.class);
    }
}
