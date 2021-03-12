package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.RejestrItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RejestrItemRepository extends JpaRepository<RejestrItem, Long> {
    @Query(value = "SELECT * FROM rejestr_item where itemstorage_id = :id",nativeQuery = true)
    List<RejestrItem> getRejestrByItemStorageId(long id);

}
