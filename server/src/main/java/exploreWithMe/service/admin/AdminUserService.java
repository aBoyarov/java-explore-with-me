package exploreWithMe.service.admin;

import exploreWithMe.model.user.UserDto;
import exploreWithMe.model.user.NewUserDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface AdminUserService {
    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    UserDto create(NewUserDto user);

    void delete(Long userId);
}
