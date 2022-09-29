package exploreWithMe.map;

import exploreWithMe.model.user.NewUserDto;
import exploreWithMe.model.user.User;
import exploreWithMe.model.user.UserDto;
import exploreWithMe.model.user.UserShortDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Andrey Boyarov
 */
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User mapToUser(UserDto userDto){
        return mapper.map(userDto, User.class);
    }

    public User mapToUser(NewUserDto newUserDto){
        return mapper.map(newUserDto, User.class);
    }

    public UserDto mapToUserDto(User user){
        return mapper.map(user, UserDto.class);
    }

    public UserShortDto mapToUserShortDto(User user){
        return mapper.map(user, UserShortDto.class);
    }


}
