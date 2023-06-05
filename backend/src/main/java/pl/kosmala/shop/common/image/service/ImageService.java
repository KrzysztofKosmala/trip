package pl.kosmala.shop.common.image.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kosmala.shop.admin.dto.UploadResponse;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.common.image.repository.ImageRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;


@Service
public class ImageService
{

    @Value("$IMAGE_UPLOAD_DIR")
    private String IMAGE_UPLOAD_DIR;
    @Value("${FETCH_IMAGE_URL}")
    private String FETCH_IMAGE_URL;
    private final ImageRepository imageRepository;

    public UploadResponse uploadFile(MultipartFile image, String country, String description)
    {
        Image model = new Image();
        String name = slugifySlug(image.getOriginalFilename());

        Path path = Paths.get(IMAGE_UPLOAD_DIR).resolve(name);

        try(InputStream inputStream = image.getInputStream())
        {
            OutputStream outputStream = Files.newOutputStream(path);
            inputStream.transferTo(outputStream);
        }catch (IOException e)  {
            throw new RuntimeException("Nie można zapisać pliku", e);
        }

        model.setThumbImage(FETCH_IMAGE_URL+name);
        model.setName(name);
        model.setDesc(description);
        model.setLocation(TripDestination.valueOf(country));
        model.setType(image.getContentType());
        saveFile(model);

        return new UploadResponse(name, "Image uploaded successfully");
    }

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
