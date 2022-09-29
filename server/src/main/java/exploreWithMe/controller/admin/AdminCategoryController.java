package exploreWithMe.controller.admin;

import exploreWithMe.model.category.CategoryDto;
import exploreWithMe.model.category.NewCategoryDto;
import exploreWithMe.service.admin.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/admin/categories")
@Slf4j
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PatchMapping
    public CategoryDto update(@Valid @RequestBody CategoryDto category){
        return adminCategoryService.update(category);
    }

    @PostMapping
    public CategoryDto addCategory(@Valid @RequestBody NewCategoryDto category){
        return adminCategoryService.create(category);
    }

    @DeleteMapping("/{catId}")
    public void delete(@PathVariable Long catId){
        adminCategoryService.delete(catId);
    }
}
