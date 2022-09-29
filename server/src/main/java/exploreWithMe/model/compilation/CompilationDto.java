package exploreWithMe.model.compilation;

import exploreWithMe.model.event.EventShortDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
@ToString
public class CompilationDto {


    private Long id;

    private List<EventShortDto> events;

    private Boolean pinned;

    private String title;

}
