package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ItemRepository;
import com.JanCode.SKPplus.web.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Item createItem(User user, ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        if(!itemDto.getImage().getContentType().equals("application/octet-stream")) {
            try {
                item.setImage(imageResizer(itemDto.getImage()));
                item.setContentType(itemDto.getImage().getContentType());
            } catch (IOException e) {
                try{
                    Resource resource = new ClassPathResource("static/img/image-solid.png");
                    File file = resource.getFile();
                    item.setImage(Files.readAllBytes(file.toPath()));
                    item.setContentType("image/png");
                } catch (IOException ee) {
                    System.out.println("Blad przy przetwarzaniu zdjecia awaryjnego upominka" + ee);
                }
                System.out.println("Blad przy przetwarzaniu zdjecia upominka" + e);
            }
        }
        else {
            System.out.println("Nie znalazłem zdjecia, daje awaryjne!");
            try{
                ClassPathResource  resource = new ClassPathResource("static/img/image-solid.png");

                byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());

                item.setImage(bdata);
                item.setContentType("image/png");
            } catch (IOException e) {
                System.out.println("Blad przy przetwarzaniu zdjecia awaryjnego upominka" + e);
            }
        }
        item.setUser(user);
        item.setTimeCreated(LocalDateTime.now());
        System.err.println("ZAPISUJE ITEM");
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(long id) {
        Item item = itemRepository.findItemById(id);
        itemRepository.delete(item);
    }

    @Override
    public Item getItem(long id) {
        Item item = itemRepository.findItemById(id);
        return item;
    }

    @Override
    public Item updateItem(long id, ItemDto itemDto) {
        Item item = getItem(id);
        if(!item.getName().equals(itemDto.getName())) item.setName(itemDto.getName());

        if(!item.getDescription().equals(itemDto.getDescription())) item.setDescription(itemDto.getDescription());
        if(!itemDto.getImage().getContentType().equals("application/octet-stream")) {
            try {
                item.setImage(imageResizer(itemDto.getImage()));
               // if(itemDto.getImage().getContentType().equals("image/jpeg")) item.setContentType("image/jpg");
                item.setContentType(itemDto.getImage().getContentType());


            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Blad IO updateItem");
            }
        }

        return itemRepository.save(item);
    }
    private byte[] imageResizer (MultipartFile imageUploaded) throws IOException {
        InputStream bity = null;
        BufferedImage image = null;
        String format = imageUploaded.getContentType().split("/")[1];
        //if(format.equals("jpeg")) format = "jpg";
        try {
            bity = imageUploaded.getInputStream();
            //System.out.println("pierwsze rozczlonawe"+bity.length);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        if(bity == null) throw new IOException();
        //if( bity.length > 0 ) {
            image = toBufferedImage(bity);
            for(String formats : ImageIO.getReaderFormatNames())
                System.out.println(formats);
            //System.out.println("po try "+bity.length);

            if (image != null){
                int width = image.getWidth();
                int height = image.getHeight();

                if (width != 200 || height != 200) {
                    image = resize(image, 200, 200);
                }
                System.out.println("po modyfikacji "+toByteArray(image,format).length);
                return toByteArray(image,format);
            }

        //}
        return null;


    }
    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    private byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        System.out.println("w trakcie modyufikacji"+bytes.length);
        return bytes;

    }
    private BufferedImage toBufferedImage(InputStream bytes)
            throws IOException {

        //ByteArrayInputStream  is = new ByteArrayInputStream();
        BufferedImage bi = ImageIO.read(bytes);
        return bi;

    }
}
