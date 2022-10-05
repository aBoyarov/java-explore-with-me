package exploreWithMeServer.service.admin;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.model.user.UserDto;
import exploreWithMeServer.model.user.NewUserDto;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface AdminUserService {
    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    UserDto create(NewUserDto user);

    void delete(Long userId) throws NotFoundException;
}
