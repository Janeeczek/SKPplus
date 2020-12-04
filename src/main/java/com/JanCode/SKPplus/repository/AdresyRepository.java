package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Adresy;
import com.JanCode.SKPplus.model.raportModel.Pozycja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresyRepository extends JpaRepository<Adresy, Long> {
}
