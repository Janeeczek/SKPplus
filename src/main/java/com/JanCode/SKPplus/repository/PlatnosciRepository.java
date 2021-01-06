package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Adresy;
import com.JanCode.SKPplus.model.raportModel.Platnosci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatnosciRepository extends JpaRepository<Platnosci, Long> {
}
