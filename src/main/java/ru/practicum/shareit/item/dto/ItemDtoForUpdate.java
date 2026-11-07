package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDtoForUpdate {

    @Size(min = 3, max = 30, message = "Имя должно быть от 3 до 30 символов")
    private String name;

    @Size(min = 3, max = 100, message = "Описание должно быть от 3 до 100 символов")
    private String description;

    private Boolean available;

}
