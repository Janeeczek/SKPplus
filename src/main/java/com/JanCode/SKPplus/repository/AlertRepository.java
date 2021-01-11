package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.Alert;
import com.JanCode.SKPplus.model.raportModel.Adresy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
