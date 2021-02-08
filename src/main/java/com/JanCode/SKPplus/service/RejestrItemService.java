package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.RejestrItem;
import com.JanCode.SKPplus.model.StorageActionType;
import com.JanCode.SKPplus.model.User;

import java.util.List;

public interface RejestrItemService {
    RejestrItem addLog(ItemStorage itemStorage, User user, String message,StorageActionType type, String value);
    RejestrItem addGiveLog(ItemStorage itemStorage, User user, int value, String numerbadania);
    RejestrItem addCreateLog(ItemStorage itemStorage, User user);
    RejestrItem addEditLog(ItemStorage itemStorage, User user);
    RejestrItem addAddQuantityLog(ItemStorage itemStorage, User user, int value);
    RejestrItem addArchiveLog(ItemStorage itemStorage, User user);

    List<RejestrItem> getAllByItemStorage(ItemStorage itemStorage);
}
