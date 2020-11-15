package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.Role;
import com.JanCode.SKPplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveUsersRepository extends JpaRepository<ActiveUsers, Long> {

    @Query(value= "SELECT * FROM activeusers WHERE email = ?1",nativeQuery = true)
    ActiveUsers findByEmail(String email);
    @Query(value= "SELECT email FROM activeusers",nativeQuery = true)
    List<ActiveUsers> getAllActiveUsers();
    @Query(value= "DELETE FROM activeusers WHERE email = ?1",nativeQuery = true)
    ActiveUsers removeByEmail(String email);

}
