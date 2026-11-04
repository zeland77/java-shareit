package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getUser(Long id) {
        return userMapper.toDto(userRepository.getUser(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + id + " не найден")));
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        if (userRepository.isEmailPresent(userDto.getEmail())) {
            throw new ConflictException("Пользователь с таким email уже существует");
        }
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto))
                .orElseThrow(() -> new NotFoundException("Не удалось сохранить пользователя")));
    }

    @Override
    public UserDto update(Long id, UserDtoForUpdate userDtoForUpdate) {
        User user = userRepository.getUser(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + id + " не найден"));

        if (userDtoForUpdate.getEmail() != null && userRepository.isEmailPresent(userDtoForUpdate.getEmail())) {
            throw new ConflictException("Пользователь с таким email уже существует");
        }

        if (userDtoForUpdate.getName() != null) {
            user.setName(userDtoForUpdate.getName());
        }
        if (userDtoForUpdate.getEmail() != null) {
            user.setEmail(userDtoForUpdate.getEmail());
        }
        return userMapper.toDto(userRepository.update(user)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + id + " не найден")));
    }

    @Override
    public void delete(Long id) {
        userRepository.findAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + id + " не найден"));
        userRepository.delete(id);
    }
}
