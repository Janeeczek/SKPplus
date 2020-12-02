package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.PlatnoscRepository;
import com.JanCode.SKPplus.repository.RaporRepository;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;
import com.JanCode.SKPplus.web.dto.RaportDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahenciDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

@Service
public class RaportServiceImpl implements RaportService{
    @Autowired
    private RaporRepository raporRepository;
    @Autowired
    private PlatnoscRepository platnoscRepository;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private UserService userService;
    @Override
    public Raport createRaport(String id,String username) {
        Raport raport = null;
        try {
            FileDB fileDB = storageService.getFile(id);
            if(fileDB == null) System.out.println("FILEDB IS NULL");
            User user = userService.findByUsername(username);
            if(user == null) System.out.println("USER IS NULL");
            MultipartFile multipartFile = new MockMultipartFile("Raport",fileDB.getData());
            File file = null;
            try (OutputStream os = new FileOutputStream(file)) {
                os.write(multipartFile.getBytes());
            }

            //multipartFile.transferTo(file);
           // FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
            JAXBContext jc = JAXBContext.newInstance(DaneRaportuDto.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();

            DaneRaportuDto daneRaportuDto = (DaneRaportuDto) unmarshaller.unmarshal(file);
            raport = new Raport(daneRaportuDto,user,fileDB);


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
    @Override
    public List<Double> getAllIncomeList() {
        return platnoscRepository.getAllIncomeList();

    }
}
