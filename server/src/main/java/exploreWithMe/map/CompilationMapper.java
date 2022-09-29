package exploreWithMe.map;

import exploreWithMe.model.compilation.Compilation;
import exploreWithMe.model.compilation.CompilationDto;
import exploreWithMe.model.compilation.NewCompilationDto;
import exploreWithMe.model.event.Event;
import exploreWithMe.model.event.EventShortDto;
import exploreWithMe.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Component
@RequiredArgsConstructor
public class CompilationMapper {

    private final ModelMapper modelMapper;

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public Compilation mapToCompilation(NewCompilationDto newCompilationDto){
        Compilation compilation = modelMapper.map(newCompilationDto, Compilation.class);
        compilation.setEvents(mapToUserShortDto(newCompilationDto.getEvents()));
        return compilation;
    }

    public CompilationDto mapToCompilationDto(Compilation compilation){
        List<EventShortDto> events = compilation.getEvents().stream()
                .map(eventMapper::mapToEventShortDto)
                .collect(Collectors.toList());
        CompilationDto compilationDto = modelMapper.map(compilation, CompilationDto.class);
        compilationDto.setEvents(events);
        return compilationDto;
    }

    private List<Event> mapToUserShortDto(List<Long> ids){
        return eventRepository.findEventsById(ids);
    }
}
