package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.web.dto.ItemDto;

public interface ItemService {
    Item createItem(User user, ItemDto itemDto);
    void deleteItem(long id);
    Item getItem(long id);
}
