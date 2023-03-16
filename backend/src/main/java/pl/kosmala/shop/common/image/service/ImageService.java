package pl.kosmala.shop.common.image.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.common.image.repository.ImageRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ImageService
{

    private final ImageRepository imageRepository;


    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public void saveFile(Image image)
    {
        imageRepository.save(image);
    }

    public Set<Image> findAllByIds(List<Long> ids)
    {
        return imageRepository.findAllByIdIn(ids);
    }

    public Page<Image> getAllImages(Pageable pageable)
    {
        return imageRepository.findAll(pageable);
    }

    public Page<Image> getImagesByDestination(Pageable pageable, TripDestination destination)
    {
        return imageRepository.findAllByDestination(pageable, destination);
    }

    public void deleteImage(Long id)
    {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent())
        {
            image.get().getProducts().forEach(product -> product.removeImage(image.get()));
            imageRepository.deleteById(id);
        }
    }
}
