package exploreWithMe.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class NewUserDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
}
