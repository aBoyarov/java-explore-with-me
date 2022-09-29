package exploreWithMe.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class EventUpdateDto {

    private Long eventId;
    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Boolean paid;
    private Integer participantLimit;
    private String title;
}
