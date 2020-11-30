package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;
import com.JanCode.SKPplus.web.dto.RaportDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahenciDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
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
    @Override
    public Raport createRaport() {
        DaneRaportuDto que;
        try {
            Resource resource = new ClassPathResource("test.xml");

            File file = resource.getFile();
            JAXBContext jc = JAXBContext.newInstance(DaneRaportuDto.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();

            DaneRaportuDto daneRaportuDto = (DaneRaportuDto) unmarshaller.unmarshal(file);
            //daneraportudto->raport


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Raport getRaportById() {
        return null;
    }

    @Override
    public Raport saveRaport() {
        return null;
    }
}
