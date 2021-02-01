package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.RejestrItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface RejestrItemRepository extends JpaRepository<RejestrItem, Long> {

}
