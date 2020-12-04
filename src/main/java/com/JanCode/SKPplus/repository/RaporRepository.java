package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.constraint.FieldMatch;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaporRepository extends JpaRepository<Raport, Long> {
    @Query(value= "SELECT * FROM raport WHERE id = ?1",nativeQuery = true)
    Raport myFindById(long id);
}
