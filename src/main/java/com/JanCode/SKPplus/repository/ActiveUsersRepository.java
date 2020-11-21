package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.Role;
import com.JanCode.SKPplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ActiveUsersRepository extends JpaRepository<ActiveUsers, Long> {

    @Query(value= "SELECT * FROM active_users WHERE email = ?1",nativeQuery = true)
    ActiveUsers findByEmail(String email);

    @Query(value= "SELECT * FROM active_users",nativeQuery = true)
    List<ActiveUsers> getAllActiveUsers();
    @Modifying
    @Query(value= "DELETE FROM active_users WHERE email =:em",nativeQuery = true)
    void deleteeByEmail(@Param("em")String email);

}
