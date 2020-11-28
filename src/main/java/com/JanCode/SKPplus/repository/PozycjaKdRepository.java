package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.PozycjaKd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozycjaKdRepository extends JpaRepository<PozycjaKd, Long> {
}
