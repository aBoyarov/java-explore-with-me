package exploreWithMeServer.service.admin.impl;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.map.CategoryMapper;
import exploreWithMeServer.model.category.Category;
import exploreWithMeServer.model.category.CategoryDto;
import exploreWithMeServer.model.category.NewCategoryDto;
import exploreWithMeServer.repository.CategoryRepository;
import exploreWithMeServer.service.admin.AdminCategoryService;
import exploreWithMeServer.valid.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final Validator validator;

    @Override
    public CategoryDto update(CategoryDto categoryDto) throws NotFoundException {
        Category category = validator.validCategory(categoryDto.getId());
        repository.save(mapper.mapToCategory(categoryDto));
        return mapper.mapToCategoryDto(category);
    }

    @Override
    public CategoryDto create(NewCategoryDto categoryDto) {
        Category category = repository.save(mapper.mapToCategory(categoryDto));
        return mapper.mapToCategoryDto(category);
    }

    @Override
    public void delete(Long catId) throws NotFoundException {
        validator.validCategory(catId);
        repository.deleteById(catId);
    }
}
