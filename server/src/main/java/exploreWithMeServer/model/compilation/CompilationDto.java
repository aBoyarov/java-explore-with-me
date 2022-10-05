package exploreWithMeServer.model.compilation;

import exploreWithMeServer.model.event.EventShortDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class CompilationDto {


    private Long id;

    private List<EventShortDto> events;

    private Boolean pinned;

    private String title;

}
