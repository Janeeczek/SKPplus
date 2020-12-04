package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Pozycja;
import com.JanCode.SKPplus.model.raportModel.Pozycje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozycjeRepository extends JpaRepository<Pozycje, Long> {
}
