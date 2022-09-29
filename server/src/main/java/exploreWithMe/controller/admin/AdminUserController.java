package exploreWithMe.controller.admin;

import exploreWithMe.model.user.UserDto;
import exploreWithMe.model.user.NewUserDto;
import exploreWithMe.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Andrey Boyarov
 */

@RestController
@RequestMapping("/admin/users")
@Slf4j
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(value = "ids") List<Long> ids,
                               @RequestParam(required = false, defaultValue = "0") Integer from,
                               @RequestParam(required = false, defaultValue = "1") Integer size){
        return adminUserService.getUsers(ids, from, size);
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody NewUserDto user) {
        return adminUserService.create(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId){
        adminUserService.delete(userId);
    }
}
