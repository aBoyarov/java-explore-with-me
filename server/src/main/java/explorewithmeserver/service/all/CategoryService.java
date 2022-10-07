package explorewithmeserver.service.all;

import explorewithmeserver.exception.NotFoundException;
import explorewithmeserver.model.category.CategoryDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface CategoryService {
    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategoryById(Long catId) throws NotFoundException;
}
