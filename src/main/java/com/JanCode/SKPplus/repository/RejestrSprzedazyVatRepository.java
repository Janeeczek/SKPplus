package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejestrSprzedazyVatRepository extends JpaRepository<RejestrSprzedazyVat, Long> {
}
