package exploreWithMe.service.admin.impl;

import exploreWithMe.model.user.User;
import exploreWithMe.model.user.UserDto;
import exploreWithMe.model.user.NewUserDto;
import exploreWithMe.page.PageLimit;
import exploreWithMe.repository.UserRepository;
import exploreWithMe.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        List<User> list = repository.findAllByIdInOrderByIdAsc(ids, PageLimit.of(from,size)).getContent();
        return list.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(NewUserDto newUserDto) {
        User user = repository.save(mapToUser(newUserDto));
        return mapToUserDto(user);
    }

    @Override
    public void delete(Long userId) {
        repository.deleteById(userId);
    }

    private User mapToUser(NewUserDto newUserDto){
        return modelMapper.map(newUserDto, User.class);
    }

    private UserDto mapToUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
