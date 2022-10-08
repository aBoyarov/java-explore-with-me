package explorewithmeserver.model.event;

import explorewithmeserver.model.category.Category;
import explorewithmeserver.model.user.UserShortDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class EventShortDto {

    private Long id;
    private String annotation;
    private Category category;
    private Integer confirmedRequests;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}
