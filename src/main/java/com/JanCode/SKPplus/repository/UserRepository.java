package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String Email);
    User findByUsername(String username);
}
