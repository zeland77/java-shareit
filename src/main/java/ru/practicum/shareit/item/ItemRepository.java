package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    List<Item> findByUserId(Long userId);

    Optional<Item> getItem(Long id);

    Optional<Item> save(Long userId, Item item);

    Optional<Item> update(Item item);

    void deleteByUserIdAndItemId(Long userId, Long itemId);

    List<Item> search(String text);
}
