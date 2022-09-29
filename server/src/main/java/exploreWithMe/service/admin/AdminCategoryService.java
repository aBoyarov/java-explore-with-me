package exploreWithMe.service.admin;

import exploreWithMe.model.category.CategoryDto;
import exploreWithMe.model.category.NewCategoryDto;

/**
 * @author Andrey Boyarov
 */
public interface AdminCategoryService {

    CategoryDto update (CategoryDto categoryDto);

    CategoryDto create(NewCategoryDto category);

    void delete(Long catId);
}
