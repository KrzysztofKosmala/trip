package pl.kosmala.shop.common.image.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kosmala.shop.common.dto.UploadResponse;
import pl.kosmala.shop.common.image.service.ImageService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.TripDestination;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class ImageController
{


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
        return ResponseEntity.ok(imageService.uploadFile(image, country, description));
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
        return imageService.getImagesByDestination(pageable, country);
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
