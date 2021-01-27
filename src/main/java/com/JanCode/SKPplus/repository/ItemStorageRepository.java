package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.ItemStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ItemStorageRepository extends JpaRepository<ItemStorage, Long> {
    @Query()
    int getItemQuantity(long id);
    @Query()
    ItemStorage getItemStorageByItemId(long id);
}
