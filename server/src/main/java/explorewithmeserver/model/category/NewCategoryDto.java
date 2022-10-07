package explorewithmeserver.model.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Andrey Boyarov
 */
@Getter
@Setter
public class NewCategoryDto {

    @NotEmpty
    private String name;
}
