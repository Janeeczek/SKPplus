package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Platnosc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlatnoscRepository extends JpaRepository<Platnosc, Long> {
    @Query(value= "SELECT SUM(platnosc.kwota_plat) FROM (((rejestry_w_raporcie inner join rejestr_sprzedazy_vat on rejestry_w_raporcie.rejestr_sprzedazy_vat_id = rejestr_sprzedazy_vat.id) inner join platnosci on rejestr_sprzedazy_vat.platnosci_id = platnosci.id)"+
            " inner join platnosc on platnosc.platnosci_id = platnosci.id) where rejestry_w_raporcie.rejestry_sprzedazy_vat_id = :id",nativeQuery = true)
    double getAllIncomeByRaportId(long id);
    @Query(value= "SELECT SUM(platnosc.kwota_plat) FROM (((rejestry_w_raporcie inner join rejestr_sprzedazy_vat on rejestry_w_raporcie.rejestr_sprzedazy_vat_id = rejestr_sprzedazy_vat.id) inner join platnosci on rejestr_sprzedazy_vat.platnosci_id = platnosci.id)"+
            " inner join platnosc on platnosc.platnosci_id = platnosci.id) where rejestry_w_raporcie.rejestry_sprzedazy_vat_id = :id AND rejestr_sprzedazy_vat.typ='Paragon'",nativeQuery = true)
    double getAllIncomeParagonByRaportId(long id);
    @Query(value= "SELECT SUM(platnosc.kwota_plat) FROM (((rejestry_w_raporcie inner join rejestr_sprzedazy_vat on rejestry_w_raporcie.rejestr_sprzedazy_vat_id = rejestr_sprzedazy_vat.id) inner join platnosci on rejestr_sprzedazy_vat.platnosci_id = platnosci.id)"+
            " inner join platnosc on platnosc.platnosci_id = platnosci.id) where rejestry_w_raporcie.rejestry_sprzedazy_vat_id = :id AND rejestr_sprzedazy_vat.typ='Faktura sprzedaży'",nativeQuery = true)
    double getAllIncomeFakturaByRaportId(long id);
    @Query(value= " SELECT SUM(platnosc.kwota_plat) FROM platnosc",nativeQuery = true)
    double getAllIncome();
    @Query(value= "SELECT platnosc.kwota_plat FROM platnosc",nativeQuery = true)
    List<Double> getAllIncomeList();
    @Query(value= "select extract(MONTH from data_kursu_plat) as month,sum(kwota_pln_plat) as total_value from platnosc group by month",nativeQuery = true)
    Map<String,Double> getIncomeForLastTwelveMonths();
    @Query(value= "SELECT DATE(data_kursu_plat)  FROM platnosc WHERE data_kursu_plat >=  CURRENT_DATE - INTERVAL 100 DAY GROUP BY DAY(data_kursu_plat)",nativeQuery = true)
    List<String> getIncomeForLast100DaysKey();
    @Query(value= "SELECT SUM(kwota_plat)  FROM platnosc WHERE data_kursu_plat >=  CURRENT_DATE - INTERVAL 100 DAY GROUP BY DAY(data_kursu_plat)",nativeQuery = true)
    List<Double> getIncomeForLast100DaysValue();
    @Query(value= "SELECT DATE(data_kursu_plat) FROM platnosc WHERE data_kursu_plat >=  CURRENT_DATE - INTERVAL 12 MONTH GROUP BY MONTH(data_kursu_plat) ",nativeQuery = true)
    List<String> getIncomeForLast12MonthsKey();
    @Query(value= "SELECT  SUM(kwota_plat) FROM platnosc WHERE data_kursu_plat >=  CURRENT_DATE - INTERVAL 12 MONTH GROUP BY MONTH(data_kursu_plat) ",nativeQuery = true)
    List<Double> getIncomeForLast12MonthsValue();

}
