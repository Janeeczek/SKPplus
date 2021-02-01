package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.raportModel.Adresy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
//@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM item where id = :id ",nativeQuery = true)
    Item findItemById(long id);
}