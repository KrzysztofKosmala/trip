package pl.kosmala.shop.admin.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.dto.UploadResponse;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.common.model.Image;
import pl.kosmala.shop.admin.service.AdminTripService;
import pl.kosmala.shop.admin.service.ImageService;
import pl.kosmala.shop.common.model.TripDestination;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminTripService adminTripService;
    private final ImageService imageService;


    @GetMapping("/trips")

    public Page<AdminTrip> getTrips(@PageableDefault(size = 30) Pageable pageable)
    {
        return adminTripService.getAllAdminTrips(pageable);
    }

    @GetMapping("/trips/{id}")
    public AdminTrip getTrip(@PathVariable Long id)
    {
        return adminTripService.getProduct(id);
    }

    @PostMapping("/trips")
    public AdminTrip addTrip(@RequestBody @Valid AdminTripDto adminProductDto)
    {
        List<Long> ids = Arrays.stream(adminProductDto.getImages()).map(Image::getId).toList();

        Set<Image> images = imageService.findAllByIds(ids);


        AdminTrip adminTrip = AdminTrip.builder()
                .name(adminProductDto.getName())
                .currency(adminProductDto.getCurrency())
                .basePrice(adminProductDto.getBasePrice())
                .desc(adminProductDto.getDesc())
                .destination(adminProductDto.getDestination())
                .slug(slugifySlug(adminProductDto.getSlug()))
                .fullDesc(adminProductDto.getFullDesc())
                .slopNearby(adminProductDto.getSlopNearby())
                .food(adminProductDto.getFood())
                .spa(adminProductDto.getSpa())
                .house(adminProductDto.getHouse())
                .wifi(adminProductDto.getWifi())
                .apartment(adminProductDto.getApartment())
                .build();

        images.forEach(image -> {
            image.addProduct(adminTrip);
        });

        AdminTrip trip = adminTripService.createTrip(adminTrip);

        return trip;

    }

    @PutMapping("/trips/{id}")
    public AdminTrip updateTrip(@RequestBody @Valid AdminTripDto adminProductDto, @PathVariable Long id)
    {
        return adminTripService.updateTrip(adminProductDto, id);
    }

    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable Long id)
    {
        adminTripService.deleteTrip(id);
    }

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
