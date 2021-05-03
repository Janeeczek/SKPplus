package com.JanCode.SKPplus.util;

import com.JanCode.SKPplus.model.ItemStorage;

import java.util.Comparator;

public class ItemStorageSorter implements Comparator<ItemStorage> {

    @Override
    public int compare(ItemStorage o1, ItemStorage o2) {
        //return o2.getItem().getName().compareToIgnoreCase(o1.getItem().getName());
        return o1.getItem().getName().compareToIgnoreCase(o2.getItem().getName());
    }
}
