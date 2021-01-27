package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.raportModel.Adresy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}