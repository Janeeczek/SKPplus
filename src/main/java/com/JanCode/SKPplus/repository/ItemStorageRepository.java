package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.ItemStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemStorageRepository extends JpaRepository<ItemStorage, Long> {

    @Query(value = "SELECT * FROM item_storage where item_id = :id ",nativeQuery = true)
    ItemStorage findItemStorageByItemId(long id);
    @Query(value = "SELECT * FROM item_storage where id = :id ",nativeQuery = true)
    ItemStorage findItemStorageById(long id);
    @Query(value = "SELECT * FROM item_storage where actual_quantity > 0 ",nativeQuery = true)
    List<ItemStorage> findAllActual();
}
