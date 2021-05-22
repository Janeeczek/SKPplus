package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.exeception.NameInUseException;
import com.JanCode.SKPplus.exeception.QuantityTooSmallException;
import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.StorageActionType;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ItemStorageRepository;
import com.JanCode.SKPplus.util.ItemStorageSorter;
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
    public List<ItemStorage> getAllActiveItemStorage() {

        List<ItemStorage> itemStorageList= itemStorageRepository.findAllNotArchived();
        itemStorageList.sort(new ItemStorageSorter());
        return itemStorageList;
    }

    @Override
    public List<ItemStorage> getAllActualItemStorage() {
        List<ItemStorage> itemStorageList= itemStorageRepository.findAllActual();
        itemStorageList.sort(new ItemStorageSorter());
        return itemStorageList;
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
        /*
        if( id== 176){
            ItemStorage zeton2Storage = itemStorageRepository.findItemStorageById(21);
            ItemStorage zeton5Storage = itemStorageRepository.findItemStorageById(98);

            int newQuantity2;
            int newQuantity5;
            int newQuantity7;

            newQuantity2 = zeton2Storage.getActualQuantity() - wydajItemDto.getQuantity();
            newQuantity5 = zeton5Storage.getActualQuantity() - wydajItemDto.getQuantity();
            newQuantity7 = itemStorage.getActualQuantity() - wydajItemDto.getQuantity();
            if(newQuantity2 < 0 || newQuantity5 < 0 || newQuantity7 < 0) throw new QuantityTooSmallException("Nie ma takiej ilości na stanie!");
            zeton2Storage.setActualQuantity(newQuantity2);
            zeton5Storage.setActualQuantity(newQuantity5);
            itemStorage.setActualQuantity(newQuantity7);
            itemStorageRepository.save(zeton2Storage);
            itemStorageRepository.save(zeton5Storage);

        }

         */
        if (itemStorage == null) {
            throw new QuantityTooSmallException("Bład! Nie ma takiego przedmiotu w bazie!");
        }
        else {
            int newQuantity = itemStorage.getActualQuantity() - wydajItemDto.getQuantity();

            if(newQuantity <0) throw new QuantityTooSmallException("Nie ma takiej ilości na stanie!");
            itemStorage.setActualQuantity(newQuantity);
        }
        return itemStorageRepository.save(itemStorage);

    }
    /*
    @Override
    public ItemStorage updateZetony() {
        //zeton 2 to itemstorageid = 21
        //zeton 5 to itemstorageid = 98
        //zeton 7 to itemstorageid = 176
        int quantity7;
        int quantity2;
        int quantity5;
        ItemStorage zeton2Storage = itemStorageRepository.findItemStorageById(21);
        ItemStorage zeton5Storage = itemStorageRepository.findItemStorageById(98);
        ItemStorage zeton7Storage = itemStorageRepository.findItemStorageById(176);
        quantity2 = zeton2Storage.getActualQuantity();
        quantity5 = zeton5Storage.getActualQuantity();
        if(quantity2 == 0)
            quantity7 = 0;
        else if (quantity5 == 0)
            quantity7 = 0;
        else {
            if(quantity5 > quantity2)
                quantity7 = quantity2;
            else
                quantity7 = quantity5;
        }
        zeton7Storage.setActualQuantity(quantity7);
        return itemStorageRepository.save(zeton7Storage);
    }

     */

    @Override
    public void deleteItemStorage(long itemStorageId) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);
        System.err.println("1");
        itemStorageRepository.delete(itemStorage);
        System.err.println("2");
        itemService.deleteItem(itemStorage.getItem().getId());
        System.err.println("3");

    }
    @Override
    public ItemStorage archiveItemStorage(long itemStorageId) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);
        itemStorage.setArchived(true);
        return itemStorageRepository.save(itemStorage);
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
        itemStorage.setActualQuantity(quantityDto.getQuantity());
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
