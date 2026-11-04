package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private static final List<Item> items = new ArrayList<>();

    @Override
    public List<Item> findByUserId(Long userId) {
        return items.stream().filter(i -> i.getOwner().equals(userId)).toList();
    }

    @Override
    public Optional<Item> getItem(Long id) {
        return items.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Item> save(Long userId, Item item) {
        item.setId(getId());
        item.setOwner(userId);
        items.add(item);
        return Optional.ofNullable(items.get(items.size() - 1));
    }

    @Override
    public void deleteByUserIdAndItemId(Long userId, Long itemId) {
        items.remove(items.stream().filter((i) -> i.getId().equals(itemId))
                .filter((i) -> i.getId().equals(userId)).findFirst().get());
    }

    @Override
    public List<Item> search(String text) {
        return items.stream()
                .filter(i -> i.getAvailable() == true)
                .filter(i -> i.getName().toLowerCase().contains(text.toLowerCase())
                        || i.getDescription().toLowerCase().contains(text.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<Item> update(Item item) {
        Item it = items.stream()
                .filter(i -> i.getOwner().equals(item.getOwner()))
                .filter(i -> i.getId().equals(item.getId())).findFirst().get();
        it.setName(item.getName());
        it.setDescription(item.getDescription());
        it.setAvailable(item.getAvailable());
        return items.stream()
                .filter(i -> i.getOwner().equals(item.getOwner()))
                .filter(i -> i.getId().equals(item.getId())).findFirst();
    }

    private long getId() {
        long lastId = items.stream()
                .mapToLong(Item::getId)
                .max()
                .orElse(0);
        return lastId + 1;
    }

}
