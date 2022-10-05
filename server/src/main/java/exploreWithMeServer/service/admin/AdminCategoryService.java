package exploreWithMeServer.service.admin;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.model.category.CategoryDto;
import exploreWithMeServer.model.category.NewCategoryDto;

/**
 * @author Andrey Boyarov
 */
public interface AdminCategoryService {

    CategoryDto update (CategoryDto categoryDto) throws NotFoundException;

    CategoryDto create(NewCategoryDto category);

    void delete(Long catId) throws NotFoundException;
}
