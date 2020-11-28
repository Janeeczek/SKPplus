package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresRepository extends JpaRepository<Adres, Long> {
}
