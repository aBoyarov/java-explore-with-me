package exploreWithMeServer.map;

import exploreWithMeServer.model.user.NewUserDto;
import exploreWithMeServer.model.user.User;
import exploreWithMeServer.model.user.UserDto;
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


    public User mapToUser(NewUserDto newUserDto){
        return mapper.map(newUserDto, User.class);
    }

    public UserDto mapToUserDto(User user){
        return mapper.map(user, UserDto.class);
    }
}
