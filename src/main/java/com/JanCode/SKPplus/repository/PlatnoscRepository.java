package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.raportModel.Platnosc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatnoscRepository extends JpaRepository<Platnosc, Long> {
    @Query(value= "SELECT SUM(platnosc.kwota_plat) FROM ((rejestry_w_raporcie inner join rejestr_sprzedazy_vat on rejestry_w_raporcie.rejestr_sprzedazy_vat_id = rejestr_sprzedazy_vat.id) inner join platnosc on rejestr_sprzedazy_vat.id = platnosc.rejestr_sprzedazy_vat_id) where rejestry_w_raporcie.rejestry_sprzedazy_vat_id = ?1",nativeQuery = true)
    double getAllIncomeByRaportId(long id);
    @Query(value= "SELECT SUM(platnosc.kwota_plat) FROM ((rejestry_w_raporcie inner join rejestr_sprzedazy_vat on rejestry_w_raporcie.rejestr_sprzedazy_vat_id = rejestr_sprzedazy_vat.id) inner join platnosc on rejestr_sprzedazy_vat.id = platnosc.rejestr_sprzedazy_vat_id)",nativeQuery = true)
    double getAllIncome();
}
