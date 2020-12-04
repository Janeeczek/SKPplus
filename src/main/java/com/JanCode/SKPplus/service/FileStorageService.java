package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.FileDBRepository;
import com.JanCode.SKPplus.web.dto.RaportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private UserService userService;

    public FileDB store(RaportDto raportDto, String username) throws IOException {
        if(raportDto.getRaport().getContentType().equals( "text/xml")) {
            String fileName = StringUtils.cleanPath(raportDto.getRaport().getOriginalFilename());
            User user = userService.findByUsername(username);
            FileDB FileDB = new FileDB(fileName, raportDto.getRaport().getContentType(), raportDto.getRaport().getBytes(),user);
            return fileDBRepository.save(FileDB);
        }
        else throw new IOException();

    }

    public FileDB store(FileDB fileDB) {
        return fileDBRepository.save(fileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.getById(id);
    }

    public List<FileDB> getAllFiles() {
        return fileDBRepository.findAll();
    }

    public void deleteFileById(String id) {
        fileDBRepository.deleteById(id);
    }

}
