package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final List<User> users = new ArrayList<>();

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> getUser(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<User> save(User user) {
        user.setId(getId());
        users.add(user);
        return users.stream().filter(u -> u.equals(user)).findFirst();
    }

    @Override
    public Optional<User> update(User user) {
        return users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .map(oldUser -> {
                    oldUser.setName(user.getName());
                    oldUser.setEmail(user.getEmail());
                    return oldUser;
                });
    }

    @Override
    public void delete(Long userId) {
        users.removeIf(u -> u.getId().equals(userId));
    }

    @Override
    public boolean isEmailPresent(String email) {
        return users.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public boolean isEmailPresent(String email, User excluseUser) {
        return users.stream()
                .filter(u -> !u.getId().equals(excluseUser.getId()))
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    private long getId() {
        long lastId = users.stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0);
        return lastId + 1;
    }

}
