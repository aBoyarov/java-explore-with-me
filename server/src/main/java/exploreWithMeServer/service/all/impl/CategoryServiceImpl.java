package exploreWithMeServer.service.all.impl;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.map.CategoryMapper;
import exploreWithMeServer.model.category.Category;
import exploreWithMeServer.model.category.CategoryDto;
import exploreWithMeServer.page.PageLimit;
import exploreWithMeServer.repository.CategoryRepository;
import exploreWithMeServer.service.all.CategoryService;
import exploreWithMeServer.valid.Validator;
import lombok.RequiredArgsConstructor;
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

    private final Validator validator;
    private final CategoryMapper mapper;
    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        List<Category> categories = repository
                .findAll(PageLimit.of(from, size))
                .getContent();
        return categories.stream().map(mapper::mapToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long catId) throws NotFoundException {
        Category category = validator.validCategory(catId);
        return mapper.mapToCategoryDto(category);
    }


}
