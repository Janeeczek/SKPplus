package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.repository.PlatnoscRepository;
import com.JanCode.SKPplus.repository.RaporRepository;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;
import com.JanCode.SKPplus.web.dto.RaportDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahenciDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class RaportServiceImpl implements RaportService{
    @Autowired
    RaporRepository raporRepository;
    @Autowired
    PlatnoscRepository platnoscRepository;
    @Override
    public Raport createRaport() {
        Raport raport = null;
        try {
            Resource resource = new ClassPathResource("Eksport.xml");

            File file = resource.getFile();
            JAXBContext jc = JAXBContext.newInstance(DaneRaportuDto.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();

            DaneRaportuDto daneRaportuDto = (DaneRaportuDto) unmarshaller.unmarshal(file);
            raport = new Raport(daneRaportuDto);


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        raporRepository.save(raport);
        return raport;
    }


    @Override
    public Raport getRaportById() {
        return null;
    }

    @Override
    public Raport saveRaport() {
        return null;

    }
    @Override
    public void removeById(long id) {
       raporRepository.deleteById(id);
       if(raporRepository.existsById(id))
            System.out.println("Błąd! Nie usunięto raportu o id: "+ id);
       else
           System.out.println("Sukces! Usunięto raport o id: "+ id);
    }

    @Override
    public void removeAll() {
        raporRepository.deleteAll();
    }

    @Override
    public double getAllIncome() {
        if(platnoscRepository.getAllIncome() > 0)
            return platnoscRepository.getAllIncome();
        else return 0;
    }
    @Override
    public List<Raport> getAllRaports() {
        return raporRepository.findAll();
    }
}
