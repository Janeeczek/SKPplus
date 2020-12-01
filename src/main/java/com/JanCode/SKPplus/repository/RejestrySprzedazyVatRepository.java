package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;
import com.JanCode.SKPplus.model.raportModel.RejestrySprzedazyVat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejestrySprzedazyVatRepository extends JpaRepository<RejestrySprzedazyVat, Long> {
}
