package exploreWithMeServer.service.all.impl;

import exploreWithMeServer.model.category.Category;
import exploreWithMeServer.model.category.CategoryDto;
import exploreWithMeServer.page.PageLimit;
import exploreWithMeServer.repository.CategoryRepository;
import exploreWithMeServer.service.all.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final ModelMapper mapper;
    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        List<Category> categories = repository
                .findAll(PageLimit.of(from, size))
                .getContent();
        return categories.stream().map(this::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        Category category = repository.findById(catId).orElseThrow();
        return toCategoryDto(category);
    }

    private CategoryDto toCategoryDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
}
