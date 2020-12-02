package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    @Query(value= "SELECT * FROM files WHERE id = ?1",nativeQuery = true)
    FileDB getById(String id);
}
