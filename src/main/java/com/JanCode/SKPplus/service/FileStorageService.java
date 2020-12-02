package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.repository.FileDBRepository;
import com.JanCode.SKPplus.web.dto.RaportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    /*
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

     */
    public FileDB store(RaportDto raportDto,String sender) throws IOException {
        if(raportDto.getRaport().getContentType().equals( "text/xml")) {
            String fileName = StringUtils.cleanPath(raportDto.getRaport().getOriginalFilename());
            FileDB FileDB = new FileDB(fileName, raportDto.getRaport().getContentType(), raportDto.getRaport().getBytes(),sender);
            return fileDBRepository.save(FileDB);
        }
        else throw new IOException();

    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
