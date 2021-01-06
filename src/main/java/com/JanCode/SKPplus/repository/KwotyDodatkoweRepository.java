package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.KwotyDodatkowe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KwotyDodatkoweRepository extends JpaRepository<KwotyDodatkowe, Long> {
}
