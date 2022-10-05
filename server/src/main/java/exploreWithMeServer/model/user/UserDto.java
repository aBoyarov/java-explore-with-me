package exploreWithMeServer.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String email;
}
