package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Platnosc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatnoscRepository extends JpaRepository<Platnosc, Long> {
}
