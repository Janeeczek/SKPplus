package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.Raport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaporRepository extends JpaRepository<Raport, Long> {
}
