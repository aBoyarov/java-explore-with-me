package exploreWithMe.service.all;

import exploreWithMe.model.category.Category;
import exploreWithMe.model.category.CategoryDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface CategoryService {
    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategoryById(Long catId);
}
