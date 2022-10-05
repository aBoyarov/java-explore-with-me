package exploreWithMeServer.service.admin.impl;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.map.UserMapper;
import exploreWithMeServer.model.user.User;
import exploreWithMeServer.model.user.UserDto;
import exploreWithMeServer.model.user.NewUserDto;
import exploreWithMeServer.page.PageLimit;
import exploreWithMeServer.repository.UserRepository;
import exploreWithMeServer.service.admin.AdminUserService;
import exploreWithMeServer.valid.Validator;
import lombok.RequiredArgsConstructor;
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
    private final Validator validator;
    private final UserMapper mapper;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        List<User> list = repository.findAllByIdInOrderByIdAsc(ids, PageLimit.of(from,size)).getContent();
        return list.stream()
                .map(mapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(NewUserDto newUserDto) {
        User user = repository.save(mapper.mapToUser(newUserDto));
        return mapper.mapToUserDto(user);
    }

    @Override
    public void delete(Long userId) throws NotFoundException {
        validator.validUser(userId);
        repository.deleteById(userId);
    }
}
