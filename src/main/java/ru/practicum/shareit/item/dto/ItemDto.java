package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 3, max = 30, message = "Имя должно быть от 3 до 30 символов")
    private String name;

    @NotBlank(message = "Описание не может быть пустым")
    @Size(min = 3, max = 100, message = "Описание должно быть от 3 до 100 символов")
    private String description;

    @NotNull(message = "Значение не должно быть null")
    private Boolean available;

}
