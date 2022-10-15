package explorewithmeserver.model.event;

import explorewithmeserver.model.Location;
import explorewithmeserver.model.category.Category;
import explorewithmeserver.model.comment.CommentDto;
import explorewithmeserver.model.user.UserShortDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class EventDto {

    private Long id;
    private String annotation;
    private Category category;
    private Integer confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private String state;
    private String title;
    private Long views;
    private List<CommentDto> comments;
}
