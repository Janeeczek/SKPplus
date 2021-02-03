package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.exeception.QuantityTooSmallException;
import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ItemStorageRepository;
import com.JanCode.SKPplus.web.dto.ItemDto;
import com.JanCode.SKPplus.web.dto.QuantityDto;
import com.JanCode.SKPplus.web.dto.WydajItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ItemStorageServiceImpl implements ItemStorageService {
    @Autowired
    private ItemStorageRepository itemStorageRepository;
    @Autowired
    private ItemService itemService;


    @Override
    public List<Item> getAllItems() {
        List<ItemStorage> itemStorageList = itemStorageRepository.findAll();
        List<Item> itemList = new ArrayList<>();
        for (ItemStorage itemStorage : itemStorageList)
            itemList.add(itemStorage.getItem());
        return itemList;
    }

    @Override
    public List<ItemStorage> getAllItemStorage() {
        return itemStorageRepository.findAll();
    }

    @Override
    public List<ItemStorage> getAllActualItemStorage() {
        return itemStorageRepository.findAllActual();
    }

    @Override
    public ItemStorage addItem(Item item, int quantity) {
        ItemStorage itemStorage = new ItemStorage(item,quantity);
        return itemStorageRepository.save(itemStorage);
    }
    @Override
    public ItemStorage updateItemStorage(ItemStorage itemStorage) {
        itemStorage.setTimeUpdated(getTimeNow());
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public ItemStorage wydajItem( WydajItemDto wydajItemDto, User user)  throws  QuantityTooSmallException {
        long id = Long.parseLong(wydajItemDto.getItemStorageId());

        ItemStorage itemStorage = itemStorageRepository.findItemStorageById(id);
        int newQuantity = itemStorage.getActualQuantity() - wydajItemDto.getQuantity();

        if(newQuantity <0) throw new QuantityTooSmallException("Nie ma takiej ilości na stanie!");
        itemStorage.setActualQuantity(newQuantity);
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public void removeItemStorage(long itemStorageId) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);
        itemStorageRepository.delete(itemStorage);
        itemService.deleteItem(itemStorage.getItem().getId());
    }

    @Override
    public int getItemQuantity(long itemStorageId) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);
        return itemStorage.getQuantity();
    }

    @Override
    public ItemStorage updateQuantity(long itemStorageId, QuantityDto quantityDto) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);
        int quant = itemStorage.getQuantity();
        int actual =  itemStorage.getActualQuantity();
        itemStorage.setActualQuantity(actual + quantityDto.getQuantity());
        itemStorage.setQuantity(quant + quantityDto.getQuantity());
        itemStorage.setTimeUpdated(getTimeNow());
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public ItemStorage getItemStorage(long itemStorageId) {
        return itemStorageRepository.findItemStorageById(itemStorageId);
    }

    private LocalDateTime getTimeNow() {
        return LocalDateTime.now();
    }
}
