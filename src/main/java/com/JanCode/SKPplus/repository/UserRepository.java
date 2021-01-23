package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value= "SELECT * FROM user WHERE email = ?1",nativeQuery = true)
    User findByEmail(String Email);
    @Query(value= "SELECT * FROM user WHERE username = ?1",nativeQuery = true)
    User findByUsername(String username);
    @Query(value= "SELECT * FROM user WHERE id = ?1",nativeQuery = true)
    User myfindById(long id);
}
