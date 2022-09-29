package exploreWithMe.service.admin.impl;

import exploreWithMe.map.CompilationMapper;
import exploreWithMe.model.compilation.Compilation;
import exploreWithMe.model.compilation.CompilationDto;
import exploreWithMe.model.compilation.NewCompilationDto;
import exploreWithMe.repository.CompilationRepository;
import exploreWithMe.service.admin.AdminCompilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {

    private final CompilationRepository compilationRepository;

    private final CompilationMapper mapper;

    @Override
    public CompilationDto create(NewCompilationDto newCompilationDto) {
        Compilation compilation = mapper.mapToCompilation(newCompilationDto);
        compilationRepository.save(compilation);
        return mapper.mapToCompilationDto(compilation);
    }

    @Override
    public void deleteById(Long compId) {
        compilationRepository.deleteById(compId);
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
