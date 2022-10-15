package explorewithmeserver.model.comment;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
@ToString
public class CommentDto {

    private Long id;
    private String text;
    private String author;
    private LocalDateTime time;
}
