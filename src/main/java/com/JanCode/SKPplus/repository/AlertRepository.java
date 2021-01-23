package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.Alert;
import com.JanCode.SKPplus.model.raportModel.Adresy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AlertRepository extends JpaRepository<Alert, Long> {
    @Query(value= "SELECT * FROM alert where user_id = :userID and viewed = 0 order by creation_date desc",nativeQuery = true)
    List<Alert> getNotViewedAlertsByUserId(Long userID);
    @Query(value= "SELECT count(*) FROM alert where user_id = :userID and viewed = 0",nativeQuery = true)
    int getUnviewedCounter(Long userID);

    @Query(value= "SELECT * FROM alert where id = :idd",nativeQuery = true)
    Alert myFindById(Long idd);


}
