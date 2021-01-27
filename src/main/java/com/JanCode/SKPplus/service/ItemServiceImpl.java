package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ItemRepository;
import com.JanCode.SKPplus.web.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Item createItem(User user, ItemDto itemDto) {
        Item item = new Item();
        System.out.println("Name: "+ itemDto.getName());
        item.setName(itemDto.getName());
        System.out.println("TAG: "+ itemDto.getTag());
        item.setTag(itemDto.getTag());
        item.setDescription(itemDto.getDescription());
        item.setImage(null);
        System.err.println("Username: " + user.getUsername());
        item.setUserCreator(user);
        item.setTimeCreated(LocalDateTime.now());
        System.err.println("ZAPISUJE ITEM");
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.delete(getItem(id));
    }

    @Override
    public Item getItem(long id) {
        Item item = itemRepository.getOne(id);
        return item;
    }
}
