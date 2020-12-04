
package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.repository.PlatnoscRepository;
import com.JanCode.SKPplus.repository.RaporRepository;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;
import com.JanCode.SKPplus.web.dto.RaportDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahenciDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RaportServiceImpl{
    @Autowired
    private RaporRepository raporRepository;
    @Autowired
    private PlatnoscRepository platnoscRepository;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private UserService userService;

    public Raport createRaport(String id,String username) {
        Raport raport = null;
        try {
            FileDB fileDB = storageService.getFile(id);
            if(fileDB == null) System.out.println("FILEDB IS NULL");
            User user = userService.findByUsername(username);
            if(user == null) System.out.println("USER IS NULL");

            InputStream targetStream = new ByteArrayInputStream(fileDB.getData());
            InputSource is = new InputSource(targetStream);

            JAXBContext jc = JAXBContext.newInstance(DaneRaportuDto.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();


            final SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            final XMLReader reader;
            try {
                reader = sax.newSAXParser().getXMLReader();
            } catch (SAXException | ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            SAXSource source = new SAXSource(reader, is);


            DaneRaportuDto daneRaportuDto = (DaneRaportuDto) unmarshaller.unmarshal(source);
            raport = new Raport(daneRaportuDto,user,fileDB);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

        raporRepository.save(raport);
        return raport;
    }
    //DB -> daneRaportuDto -> Marshal -> FileDb ->storeFile
    public byte[] createFileFromRaport(long id, String username) {
        byte[] resoult = null;
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DaneRaportuDto.class);
            Raport raport = raporRepository.myFindById(id);
            DaneRaportuDto daneRaportuDto = new DaneRaportuDto(raport);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,false);
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1250");
           // marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
            //marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"windows-1250\"?>");
            marshaller.setProperty("com.sun.xml.bind.marshaller.CharacterEscapeHandler", new CharacterEscapeHandler() {
                @Override
                public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
                    writer.write(ac, i, j);
                }
            });
            marshaller.marshal(daneRaportuDto,byteArrayOutputStream);
            resoult = byteArrayOutputStream.toByteArray();


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return resoult;

    }

    public Raport getRaportById() {
        return null;
    }


    public void removeById(long id) {
       raporRepository.deleteById(id);
       if(raporRepository.existsById(id))
            System.out.println("Błąd! Nie usunięto raportu o id: "+ id);
       else
           System.out.println("Sukces! Usunięto raport o id: "+ id);
    }


    public void removeAll() {
        raporRepository.deleteAll();
    }


    public double getAllIncome() {
       // if(platnoscRepository.getAllIncome() > 0)
         //   return platnoscRepository.getAllIncome();
        //else return 0;
        return 0;
    }

    public List<Raport> getAllRaports() {
        return raporRepository.findAll();
    }

    public List<Double> getAllIncomeList() {
        //return platnoscRepository.getAllIncomeList();
return null;
    }
}
