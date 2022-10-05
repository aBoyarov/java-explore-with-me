package exploreWithMeServer.controller.admin;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.model.user.UserDto;
import exploreWithMeServer.model.user.NewUserDto;
import exploreWithMeServer.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/admin/users")
@Slf4j
@Validated
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(value = "ids") List<Long> ids,
                               @Min(0) @RequestParam(required = false, defaultValue = "0") Integer from,
                               @Min(1) @RequestParam(required = false, defaultValue = "10") Integer size){
        return adminUserService.getUsers(ids, from, size);
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody NewUserDto user) {
        return adminUserService.create(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) throws NotFoundException {
        adminUserService.delete(userId);
    }
}
