package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.constraint.FieldMatch;
import com.JanCode.SKPplus.model.Raport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaporRepository extends JpaRepository<Raport, Long> {
}
