package exploreWithMeServer.controller.admin;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.model.category.CategoryDto;
import exploreWithMeServer.model.category.NewCategoryDto;
import exploreWithMeServer.service.admin.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/admin/categories")
@Slf4j
@Validated
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PatchMapping
    public CategoryDto update(@Valid @RequestBody CategoryDto category) throws NotFoundException {
        return adminCategoryService.update(category);
    }

    @PostMapping
    public CategoryDto addCategory(@Valid @RequestBody NewCategoryDto category){
        return adminCategoryService.create(category);
    }

    @DeleteMapping("/{catId}")
    public void delete(@PathVariable Long catId) throws NotFoundException {
        adminCategoryService.delete(catId);
    }
}
