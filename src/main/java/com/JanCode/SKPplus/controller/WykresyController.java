package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.InfoModel.WykresKolowyData;
import com.JanCode.SKPplus.model.InfoModel.WykresLiniowyData;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WykresyController {

    @Autowired
    private RaportServiceImpl raportService;

    @ModelAttribute("allIncome")
    public String getAllIncome() {
        List<Raport> raports = raportService.getAllRaports();
        if (raports.size() > 0)
        {
            System.out.println("Lista INCOME != null");
            Double income = raportService.getAllIncome();
            if (income == null || income <1.0) {
                return "Brak dochodu";
            } else if (income < 1000.0 ) {
                if(income % 1 ==0)
                    return String.format("%d",income.longValue())+ " zł";
                else
                    return String.format("%s",income) + " zł";
            } else if (income < 10000.0 ) {
                if(income % 1 ==0){
                    String value = Double.toString(income);
                    value = value.substring(0,2) + " " + value.substring(2,value.length())+ " zł";
                    return value;
                }
                else {
                    String value = Double.toString(income);
                    value = value.substring(0,2) + " " + value.substring(2,value.length())+ " zł";
                    return value;
                }

            } else if (income < 100000.0 ) {
                if(income % 1 ==0){
                    String value = Double.toString(income);
                    value = value.substring(0,2) + " " + value.substring(2,value.length()-2 )+ " zł";
                    return value;
                }
                else {
                    String value = Double.toString(income);
                    value = value.substring(0,2) + " " + value.substring(2,value.length())+ " zł";
                    return value;
                }

            }

        }
        return "Brak dochodu";


    }
    @ModelAttribute("daneWykresuKolowego")
    public WykresKolowyData getDaneWykresuKolowego() {
        List<Raport> raports = raportService.getAllRaports();
        if ( raports.isEmpty() == false && raports != null  )
        {
            System.out.println("Lista wykresu != null");
            List<Double> lista = raportService.getAllIncomeList();
            int osobowe = 0;
            int ciezarowe = 0;
            int inne = 0;
            for (Double a : lista) {
                if(a == 99.0) osobowe++;
                else if(a == 177.0 || a == 154.0 || a== 200.0 || a == 178.0 || a == 162.0 || a == 79) ciezarowe++;
                else inne++;
            }
            System.out.println("ILE= "+lista.size()+"  Osobowe= "+osobowe+" Ciezarowe= "+ciezarowe+" Inne= "+inne);
            return new WykresKolowyData(osobowe,ciezarowe,inne);
        }
        else return new WykresKolowyData(2,1,4);

    }
    @ModelAttribute("daneWykresuLiniowy")
    public WykresLiniowyData getDaneWykresuLiniowego() {
        List<Double> income = new ArrayList<>(List.of(1000.0,3000.0));
        return new WykresLiniowyData(income);

    }
}
