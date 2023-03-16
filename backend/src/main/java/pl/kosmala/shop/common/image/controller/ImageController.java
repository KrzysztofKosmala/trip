package pl.kosmala.shop.common.image.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kosmala.shop.admin.dto.UploadResponse;
import pl.kosmala.shop.common.image.service.ImageService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.TripDestination;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class ImageController
{
    @Value("${FETCH_IMAGE_URL}")
    private String FETCH_IMAGE_URL;
    private final ImageService imageService;


    @DeleteMapping("/images/{id}")
    public void deleteImage(@PathVariable Long id)
    {
        imageService.deleteImage(id);
    }

    @PostMapping("/images/upload-image")
    public ResponseEntity<UploadResponse> uploadImage
            (
                    @RequestParam("image") MultipartFile image,
                    @RequestParam("country") String country,
                    @RequestParam("description") String description
            )
    {
        Image model = new Image();
        String name = slugifySlug(image.getOriginalFilename());

        String uploadDir = "./data/productImages/";


        Path path = Paths.get(uploadDir).resolve(name);

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
        imageService.saveFile(model);
        return ResponseEntity.ok(new UploadResponse(name, "Image uploaded successfully"));

    }


    @GetMapping("/images")
    public Page<Image> getImages(@PageableDefault(size = 30) Pageable pageable)
    {
        return imageService.getAllImages(pageable);
    }
    @GetMapping("/imagesByCountry")
    public Page<Image> getImagesByCountry
            (
                    @PageableDefault(size = 30) Pageable pageable,
                    @RequestParam(required = false) TripDestination country
            )
    {
        int i=0;
        Page<Image> imagesByDestination = imageService.getImagesByDestination(pageable, country);
        return imagesByDestination;
    }

    @GetMapping("/data/productImage/{name}")
    public ResponseEntity<Resource> serveFile(@PathVariable String name) throws IOException
    {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        String uploadDir = "./data/productImages/";
        Resource resource = fileSystemResourceLoader.getResource(uploadDir + name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(name)))
                .body(resource);
    }
}
