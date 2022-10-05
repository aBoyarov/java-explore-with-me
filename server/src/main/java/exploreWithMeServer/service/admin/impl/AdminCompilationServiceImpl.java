package exploreWithMeServer.service.admin.impl;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.map.CompilationMapper;
import exploreWithMeServer.model.compilation.Compilation;
import exploreWithMeServer.model.compilation.CompilationDto;
import exploreWithMeServer.model.compilation.NewCompilationDto;
import exploreWithMeServer.model.event.Event;
import exploreWithMeServer.repository.CompilationRepository;
import exploreWithMeServer.service.admin.AdminCompilationService;
import exploreWithMeServer.valid.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {

    private final CompilationRepository repository;
    private final CompilationMapper mapper;
    private final Validator validator;

    @Override
    public CompilationDto create(NewCompilationDto newCompilationDto) {
        Compilation compilation = mapper.mapToCompilation(newCompilationDto);
        repository.save(compilation);
        return mapper.mapToCompilationDto(compilation);
    }

    @Override
    public void deleteById(Long compId) throws NotFoundException {
        validator.validCompilation(compId);
        repository.deleteById(compId);
    }

    @Override
    public void deleteEventFromCompilation(Long compId, Long eventId) throws NotFoundException {
        Compilation compilation = validator.validCompilation(compId);
        Event event = validator.validEvent(eventId);
        compilation.getEvents().remove(event);
        repository.save(compilation);
    }

    @Override
    public CompilationDto addEventToCompilation(Long compId, Long eventId) throws NotFoundException {
        Compilation compilation = validator.validCompilation(compId);
        Event event = validator.validEvent(eventId);
        compilation.getEvents().add(event);
        repository.save(compilation);
        return mapper.mapToCompilationDto(compilation);
    }

    @Override
    public void deleteCompilationFromMainPage(Long compId) throws NotFoundException {
        Compilation compilation = validator.validCompilation(compId);
        compilation.setPinned(false);
        repository.save(compilation);

    }

    @Override
    public void pinCompilationOnMainPage(Long compId) throws NotFoundException {
        Compilation compilation = validator.validCompilation(compId);
        compilation.setPinned(true);
        repository.save(compilation);
    }
}
