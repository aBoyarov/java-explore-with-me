package exploreWithMeServer.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import exploreWithMeServer.model.Location;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class NewEventDto {

    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;

}
