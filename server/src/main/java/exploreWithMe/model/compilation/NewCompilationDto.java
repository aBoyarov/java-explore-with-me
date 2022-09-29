package exploreWithMe.model.compilation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class NewCompilationDto {

    private List<Long> events;
    private Boolean pinned;
    private String title;
}
