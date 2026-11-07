package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoForUpdate;

import java.util.List;

public interface ItemService {

    List<ItemDto> getItems(Long userId);

    ItemDto getItem(Long id);

    ItemDto addNewItem(Long userId, ItemDto itemDto);

    void deleteItem(Long userId, Long id);

    List<ItemDto> search(String text);

    ItemDto update(Long userId, Long id, ItemDtoForUpdate itemDtoForUpdate);
}
