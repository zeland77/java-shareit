package ru.practicum.shareit.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> getUser(Long id);

    Optional<User> save(User user);

    Optional<User> update(User user);

    void delete(Long userId);

    boolean isEmailPresent(String email);
}
