package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoForUpdate;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;

    @Override
    public List<ItemDto> getItems(Long userId) {
        return itemMapper.toDtoList(itemRepository.findByUserId(userId));
    }

    @Override
    public ItemDto getItem(Long id) {
        return itemMapper.toDto(itemRepository.getItem(id)
                .orElseThrow(() -> new NotFoundException("Вещь с id = " + id + " не найдена")));
    }

    @Override
    public ItemDto addNewItem(Long userId, ItemDto itemDto) {
        if (userRepository.getUser(userId).isEmpty()) {
            throw new NotFoundException("Пользователь с id = " + userId + " не найден");
        }
        return itemMapper.toDto(itemRepository.save(userId, itemMapper.toEntity(itemDto))
                .orElseThrow(() -> new NotFoundException("Не удалось сохранить вещь")));
    }

    @Override
    public void deleteItem(Long userId, Long id) {
        itemRepository.deleteByUserIdAndItemId(userId, id);
    }

    @Override
    public List<ItemDto> search(String text) {
        if (text == null || text.isBlank()) {
            return new ArrayList<>();
        }
        return itemMapper.toDtoList(itemRepository.search(text));
    }

    @Override
    public ItemDto update(Long userId, Long id, ItemDtoForUpdate itemDtoForUpdate) {
        if (userRepository.getUser(userId).isEmpty()) {
            throw new NotFoundException("Пользователь с id = " + userId + " не найден");
        }
        Item item = itemRepository.findByUserId(userId).stream().filter(i -> i.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("Вещь с id = " + id + " у пользователя с id = " + userId + " не найдена"));

        if (itemDtoForUpdate.getName() != null) {
            item.setName(itemDtoForUpdate.getName());
        }
        if (itemDtoForUpdate.getDescription() != null) {
            item.setDescription(itemDtoForUpdate.getDescription());
        }
        if (itemDtoForUpdate.getAvailable() != null) {
            item.setAvailable(itemDtoForUpdate.getAvailable());
        }
        item.setOwner(userId);
        item.setId(id);
        return itemMapper.toDto(itemRepository.update(item)
                .orElseThrow(() -> new NotFoundException("Не удалось обновить данные")));
    }
}
