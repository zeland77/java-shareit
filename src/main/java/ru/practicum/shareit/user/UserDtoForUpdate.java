package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDtoForUpdate {

    @Size(min = 3, max = 30, message = "Имя должно быть от 3 до 30 символов")
    private String name;

    @Email(message = "Некорректный формат email")
    private String email;

}
