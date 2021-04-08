package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.RejestrItem;
import com.JanCode.SKPplus.model.StorageActionType;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.RejestrItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RejestrItemServiceImpl implements RejestrItemService {
    @Autowired
    private RejestrItemRepository rejestrItemRepository;
    @Override
    public RejestrItem addLog(ItemStorage itemStorage, User user, String message, StorageActionType type, String value) {
        return null;
    }

    @Override
    public RejestrItem addGiveLog(ItemStorage itemStorage, User user, int value, String numerbadania) {
        RejestrItem rejestrItem = new RejestrItem();
        rejestrItem.setItemStorage(itemStorage);
        rejestrItem.setUser(user);
        rejestrItem.setCreateDateTime(LocalDateTime.now());
        rejestrItem.setType(StorageActionType.GIVE);
        rejestrItem.setValue(value);
        rejestrItem.setNumerBadania(numerbadania);
        return rejestrItemRepository.save(rejestrItem);
    }
    @Override
    public RejestrItem addGiveInternLog(ItemStorage itemStorage, User user, int value, String numerbadania) {
        RejestrItem rejestrItem = new RejestrItem();
        rejestrItem.setItemStorage(itemStorage);
        rejestrItem.setUser(user);
        rejestrItem.setCreateDateTime(LocalDateTime.now());
        rejestrItem.setType(StorageActionType.GIVEINTERN);
        rejestrItem.setValue(value);
        rejestrItem.setNumerBadania(numerbadania);
        return rejestrItemRepository.save(rejestrItem);
    }

    @Override
    public RejestrItem addCreateLog(ItemStorage itemStorage, User user) {

        RejestrItem rejestrItem = new RejestrItem();
        rejestrItem.setItemStorage(itemStorage);
        rejestrItem.setUser(user);
        rejestrItem.setCreateDateTime(LocalDateTime.now());
        rejestrItem.setType(StorageActionType.CREATE);
        rejestrItem.setNumerBadania(null);
        rejestrItem.setValue(itemStorage.getQuantity());

        return rejestrItemRepository.save(rejestrItem);
    }

    @Override
    public RejestrItem addEditLog(ItemStorage itemStorage, User user) {

        RejestrItem rejestrItem = new RejestrItem();
        rejestrItem.setItemStorage(itemStorage);
        rejestrItem.setUser(user);
        rejestrItem.setCreateDateTime(LocalDateTime.now());
        rejestrItem.setType(StorageActionType.EDIT);
        rejestrItem.setNumerBadania(null);
        rejestrItem.setValue(itemStorage.getQuantity());

        return rejestrItemRepository.save(rejestrItem);
    }

    @Override
    public RejestrItem addAddQuantityLog(ItemStorage itemStorage, User user, int value) {
        RejestrItem rejestrItem = new RejestrItem();
        rejestrItem.setItemStorage(itemStorage);
        rejestrItem.setUser(user);
        rejestrItem.setCreateDateTime(LocalDateTime.now());
        rejestrItem.setType(StorageActionType.ADDQUANTITY);
        rejestrItem.setNumerBadania(null);
        rejestrItem.setValue(value);

        return rejestrItemRepository.save(rejestrItem);
    }

    @Override
    public RejestrItem addArchiveLog(ItemStorage itemStorage, User user) {
        RejestrItem rejestrItem = new RejestrItem();
        rejestrItem.setItemStorage(itemStorage);
        rejestrItem.setUser(user);
        rejestrItem.setCreateDateTime(LocalDateTime.now());
        rejestrItem.setType(StorageActionType.ARCHIVE);
        rejestrItem.setNumerBadania(null);
        rejestrItem.setValue(0);

        return rejestrItemRepository.save(rejestrItem);
    }

    @Override
    public void deleteRejestrItem(ItemStorage itemStorage) {
        List<RejestrItem> rejestrItemList = getAllByItemStorage(itemStorage);
        for(RejestrItem rejestrItem : rejestrItemList) rejestrItemRepository.delete(rejestrItem);

    }

    @Override
    public List<RejestrItem> getAllByItemStorage(ItemStorage itemStorage) {
        return rejestrItemRepository.getRejestrByItemStorageId(itemStorage.getId());
    }

    @Override
    public List<RejestrItem> getAllFromToday() {
        return rejestrItemRepository.getAllFromToday();
    }
}
