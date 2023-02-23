package pl.kosmala.shop.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Image> getAllImages(Pageable pageable)
    {
        return imageRepository.findAll(pageable);
    }

    public void deleteTrip(Long id)
    {
        imageRepository.deleteById(id);
    }
}
