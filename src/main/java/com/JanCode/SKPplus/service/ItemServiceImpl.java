package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ItemRepository;
import com.JanCode.SKPplus.web.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Item createItem(User user, ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setTag(itemDto.getTag());
        item.setDescription(itemDto.getDescription());
        if(itemDto.getImage() != null) {
            try {
                item.setImage(imageResizer(itemDto.getImage()));
            } catch (IOException e) {
                item.setImage(null);
                e.printStackTrace();
            }
        }
        else item.setImage(null);
        item.setUser(user);
        item.setTimeCreated(LocalDateTime.now());
        System.err.println("ZAPISUJE ITEM");
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.delete(getItem(id));
    }

    @Override
    public Item getItem(long id) {
        Item item = itemRepository.getOne(id);
        return item;
    }
    private byte[] imageResizer (MultipartFile imageUploaded) throws IOException {
        byte[] bity = null;
        BufferedImage image = null;
        String format = imageUploaded.getContentType().split("/")[1];

        try {
            bity = imageUploaded.getBytes();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        if(bity == null) throw new IOException();
        if( bity.length > 0 ) {
            try {
                image = toBufferedImage(imageUploaded.getBytes());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            if (image != null){
                int width = image.getWidth();
                int height = image.getHeight();

                if (width != 200 || height != 200) {
                    image = resize(image, 200, 200);
                }
                return toByteArray(image,format);
            }

        }
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
        return bytes;

    }
    private BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }
}
