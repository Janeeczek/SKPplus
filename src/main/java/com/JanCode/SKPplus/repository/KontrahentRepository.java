package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontrahentRepository extends JpaRepository<Kontrahent, Long> {
}
