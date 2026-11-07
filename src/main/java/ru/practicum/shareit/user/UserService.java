package ru.practicum.shareit.user;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUser(Long id);

    UserDto saveUser(UserDto userDto);

    UserDto update(Long id, UserDtoForUpdate userDtoForUpdate);

    void delete(Long id);
}
