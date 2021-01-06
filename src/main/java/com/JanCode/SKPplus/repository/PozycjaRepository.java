package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Pozycja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozycjaRepository extends JpaRepository<Pozycja, Long> {
}
