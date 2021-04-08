package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.*;

import java.util.List;

public interface RejestrItemService {
    RejestrItem addLog(ItemStorage itemStorage, User user, String message,StorageActionType type, String value);
    RejestrItem addGiveLog(ItemStorage itemStorage, User user, int value, String numerbadania);
    RejestrItem addGiveInternLog(ItemStorage itemStorage, User user, int value, String numerbadania);
    RejestrItem addCreateLog(ItemStorage itemStorage, User user);
    RejestrItem addEditLog(ItemStorage itemStorage, User user);
    RejestrItem addAddQuantityLog(ItemStorage itemStorage, User user, int value);
    RejestrItem addArchiveLog(ItemStorage itemStorage, User user);
    void deleteRejestrItem(ItemStorage itemStorage);

    List<RejestrItem> getAllByItemStorage(ItemStorage itemStorage);
    List<RejestrItem> getAllFromToday();
}
