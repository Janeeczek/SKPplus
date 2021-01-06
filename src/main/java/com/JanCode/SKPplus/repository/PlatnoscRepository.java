package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Platnosc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatnoscRepository extends JpaRepository<Platnosc, Long> {
    @Query(value= "SELECT SUM(platnosc.kwota_plat) FROM ((rejestry_w_raporcie inner join rejestr_sprzedazy_vat on rejestry_w_raporcie.rejestr_sprzedazy_vat_id = rejestr_sprzedazy_vat.id) inner join"+
            " platnosc on rejestr_sprzedazy_vat.id = platnosc.rejestr_sprzedazy_vat_id) where rejestry_w_raporcie.rejestry_sprzedazy_vat_id = ?1",nativeQuery = true)
    double getAllIncomeByRaportId(long id);
    @Query(value= " SELECT SUM(platnosc.kwota_plat) FROM platnosc",nativeQuery = true)
    double getAllIncome();
    @Query(value= "SELECT platnosc.kwota_plat FROM platnosc",nativeQuery = true)
    List<Double> getAllIncomeList();
    @Query(value= " SELECT SUM(platnosc.kwota_plat) FROM platnosc WHERE YEAR(data_kursu_plat) = :year AND MONTH(data_kursu_plat) = :month",nativeQuery = true)
    double getIncomeByMonth(@Param("month")int month,@Param("year")int year);
    @Query(value= "SELECT SUM(platnosc.kwota_plat) FROM platnosc WHERE YEAR(data_kursu_plat) = :year AND MONTH(data_kursu_plat) = :month AND DAY(data_kursu_plat) BETWEEN :fromDay AND :untilDay",nativeQuery = true)
    double getAllIncomeByDate(@Param("fromDay")int fromDay,@Param("untilDay")int untilDay,@Param("month")int month,@Param("year")int year);
}
