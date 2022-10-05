package exploreWithMeServer.service.all;

import exploreWithMeServer.model.category.CategoryDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface CategoryService {
    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategoryById(Long catId);
}
