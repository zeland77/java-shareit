package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoForUpdate;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAll(@Valid @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getItems(userId);
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@Valid @PathVariable("id") Long id) {
        return itemService.getItem(id);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam("text") String text) {
        return itemService.search(text);
    }

    @PostMapping
    public ItemDto add(@Valid @RequestHeader("X-Sharer-User-Id") Long userId,
                       @Valid @RequestBody ItemDto itemDto) {
        return itemService.addNewItem(userId, itemDto);
    }

    @PatchMapping("/{id}")
    public ItemDto update(@Valid @RequestHeader("X-Sharer-User-Id") Long userId,
                          @Valid @PathVariable("id") Long id,
                          @RequestBody ItemDtoForUpdate itemDtoForUpdate) {
        return itemService.update(userId, id, itemDtoForUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@Valid @RequestHeader("X-Sharer-User-Id") Long userId,
                           @Valid @PathVariable("id") Long id) {
        itemService.deleteItem(userId, id);
    }

}
