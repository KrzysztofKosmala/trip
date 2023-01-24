package pl.kosmala.shop.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kosmala.shop.admin.model.Image;
import pl.kosmala.shop.admin.repository.ImageRepository;

import java.io.IOException;


@Service
public class ImageService
{

    private final ImageRepository imageRepository;


    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public void saveFile(Image image) {
        imageRepository.save(image);
    }
}
