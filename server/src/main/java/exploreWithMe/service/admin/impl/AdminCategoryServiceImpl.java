package exploreWithMe.service.admin.impl;

import exploreWithMe.model.category.Category;
import exploreWithMe.model.category.CategoryDto;
import exploreWithMe.model.category.NewCategoryDto;
import exploreWithMe.repository.CategoryRepository;
import exploreWithMe.service.admin.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final CategoryRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Category category = repository.saveAndFlush(mapToCategory(categoryDto));
        return mapToCategoryDto(category);
    }

    @Override
    public CategoryDto create(NewCategoryDto categoryDto) {
        Category category = repository.saveAndFlush(mapToCategory(categoryDto));
        return mapToCategoryDto(category);
    }

    @Override
    public void delete(Long catId) {
        repository.deleteById(catId);
    }

    private Category mapToCategory(NewCategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    private Category mapToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
