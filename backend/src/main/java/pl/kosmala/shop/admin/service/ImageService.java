package pl.kosmala.shop.admin.service;

import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.model.Image;
import pl.kosmala.shop.common.repository.ImageRepository;


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
