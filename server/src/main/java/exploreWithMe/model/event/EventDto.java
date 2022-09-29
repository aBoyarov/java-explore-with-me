package exploreWithMe.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import exploreWithMe.model.Location;
import exploreWithMe.model.category.Category;
import exploreWithMe.model.user.User;
import exploreWithMe.model.user.UserShortDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private String state;
    private String title;
    private Integer views;
}
