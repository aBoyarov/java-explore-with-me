package exploreWithMeServer.map;

import exploreWithMeServer.model.category.Category;
import exploreWithMeServer.model.category.CategoryDto;
import exploreWithMeServer.model.category.NewCategoryDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Andrey Boyarov
 */
@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ModelMapper mapper;

    public Category mapToCategory(NewCategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    public Category mapToCategory(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    public CategoryDto mapToCategoryDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
}
