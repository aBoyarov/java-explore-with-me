package exploreWithMe.service.all.impl;

import exploreWithMe.model.category.Category;
import exploreWithMe.model.category.CategoryDto;
import exploreWithMe.page.PageLimit;
import exploreWithMe.repository.CategoryRepository;
import exploreWithMe.service.all.CategoryService;
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
