package exploreWithMe.controller.all;

import exploreWithMe.model.category.Category;
import exploreWithMe.model.category.CategoryDto;
import exploreWithMe.service.all.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getCategories(@Min(0) @RequestParam(required = false, defaultValue = "0") Integer from,
                                           @Min(1) @RequestParam(required = false, defaultValue = "1") Integer size){
        return categoryService.getCategories(from, size);
    }


    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId){
        return categoryService.getCategoryById(catId);
    }
}
