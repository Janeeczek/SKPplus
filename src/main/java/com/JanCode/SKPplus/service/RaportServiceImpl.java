
package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.model.InfoModel.WykresKolowyData;
import com.JanCode.SKPplus.model.InfoModel.WykresLiniowyData;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.PlatnoscRepository;
import com.JanCode.SKPplus.repository.RaportRepository;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.util.*;

@Service
public class RaportServiceImpl{
    @Autowired
    private RaportRepository raportRepository;
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
            if(is == null) System.out.println("INPUT SOURCE IS NULL");
            JAXBContext jc = JAXBContext.newInstance(DaneRaportuDto.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();


            final SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            final XMLReader reader;
            try {
                reader = sax.newSAXParser().getXMLReader();
            } catch (SAXException | ParserConfigurationException e) {
                 System.out.println("SAXException !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                throw new RuntimeException(e);
            }
            SAXSource source = new SAXSource(reader, is);
            if(source == null) System.out.println("SAXSource SOURCE IS NULL");

            DaneRaportuDto daneRaportuDto = (DaneRaportuDto) unmarshaller.unmarshal(source);
            raport = new Raport(fileDB.getName(),daneRaportuDto,user,fileDB);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

        raportRepository.save(raport);
        return raport;
    }
    //DB -> daneRaportuDto -> Marshal -> FileDb ->storeFile
    public byte[] createFileFromRaport(long id, String username) {
        byte[] resoult = null;
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        xmlOutputFactory.setProperty("escapeCharacters", false);


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DaneRaportuDto.class);
            Raport raport = raportRepository.myFindById(id);
            DaneRaportuDto daneRaportuDto = new DaneRaportuDto(raport);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1250");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            /*
            marshaller.setProperty("com.sun.xml.bind.marshaller.CharacterEscapeHandler", new CharacterEscapeHandler() {
                @Override
                public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
                    writer.write(ac, i, j);
                }
            });

             */
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(byteArrayOutputStream, (String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
            marshaller.marshal(daneRaportuDto, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();

            resoult = byteArrayOutputStream.toByteArray();



        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resoult;

    }
    public byte[] createFileFromRaportExtra(long id, String username) {
        byte[] resoult = null;
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        xmlOutputFactory.setProperty("escapeCharacters", false);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DaneRaportuDto.class);
            Raport raport = raportRepository.myFindById(id);
            DaneRaportuDto daneRaportuDto = new DaneRaportuDto(raport);
            daneRaportuDto.getKONTRAHENCI().setBAZA_DOC_ID("STACJ");
            daneRaportuDto.getKONTRAHENCI().setBAZA_ZRD_ID("STACJ");
            daneRaportuDto.getREJESTRY_SPRZEDAZY_VAT().setBAZA_DOC_ID("STACJ");
            daneRaportuDto.getREJESTRY_SPRZEDAZY_VAT().setBAZA_ZRD_ID("STACJ");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1250");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            /*
            marshaller.setProperty("com.sun.xml.bind.marshaller.CharacterEscapeHandler", new CharacterEscapeHandler() {
                @Override
                public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
                    writer.write(ac, i, j);
                }
            });

             */
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(byteArrayOutputStream, (String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
            marshaller.marshal(daneRaportuDto, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();

            resoult = byteArrayOutputStream.toByteArray();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resoult;

    }

    public Raport getRaportById(long id) {
        return raportRepository.myFindById(id);
    }


    public void removeById(long id) {
       raportRepository.deleteById(id);
       if(raportRepository.existsById(id))
            System.out.println("Błąd! Nie usunięto raportu o id: "+ id);
       else
           System.out.println("Sukces! Usunięto raport o id: "+ id);
    }


    public void removeAll() {
        raportRepository.deleteAll();
    }


    public double getAllIncome() {
        if(platnoscRepository.getAllIncome() > 0)
            return platnoscRepository.getAllIncome();
        else return 0;
       // return 0;
    }

    public List<Raport> getAllRaports() {
        List<Raport> raports = raportRepository.myFindAll();
        return raports;
    }

    public List<Double> getAllIncomeList() {
        List<Double> wplywy = platnoscRepository.getAllIncomeList();
        return wplywy;
        //return null;
    }



    public String getAllIncomeString() {
        List<Raport> raports = getAllRaports();
        if (raports.size() > 0)
        {
            //System.out.println("Lista INCOME != null");
            Double income = getAllIncome();
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
    public WykresKolowyData getDaneWykresuKolowego() {
        List<Raport> raports = getAllRaports();
        if ( raports.isEmpty() == false && raports != null  )
        {
            List<Double> lista = getAllIncomeList();
            int osobowe = 0;
            int ciezarowe = 0;
            int inne = 0;
            for (Double a : lista) {
                if(a == 99.0) osobowe++;
                else if(a == 177.0 || a == 154.0 || a== 200.0 || a == 178.0 || a == 162.0 || a == 79) ciezarowe++;
                else inne++;
            }

            return new WykresKolowyData(osobowe,ciezarowe,inne);
        }
        else return new WykresKolowyData(2,1,4);

    }
    public WykresLiniowyData getDaneWykresuLiniowego100days() {

        List<String> keys = platnoscRepository.getIncomeForLast100DaysKey();
        List<Double> values = platnoscRepository.getIncomeForLast100DaysValue();
        List<String> nkeys = new ArrayList<>();
        List<Double> nvalues = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();
        for(int i =0 ;i< keys.size();i++){
            map.put(keys.get(i),values.get(i) );
        }
        Map<String, Double> treeMap = new TreeMap<>(map);
        for(String s : treeMap.keySet()){
            nkeys.add(s);
            nvalues.add(treeMap.get(s));
        }

        return new WykresLiniowyData(nkeys,nvalues);
    }
    public WykresLiniowyData getDaneWykresuLiniowego12months() {

        List<String> keys = platnoscRepository.getIncomeForLast12MonthsKey();
        List<Double> values = platnoscRepository.getIncomeForLast12MonthsValue();

        return new WykresLiniowyData(keys,values);
    }
}
