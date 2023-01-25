package pl.kosmala.shop.admin.controller;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.dto.UploadResponse;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.model.Image;
import pl.kosmala.shop.admin.service.AdminTripService;
import pl.kosmala.shop.admin.service.ImageService;

import javax.validation.Valid;
import java.io.IOException;

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
        return adminTripService.createTrip(AdminTrip.builder()
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
                .build()
        );

    }

    @PutMapping("/trips/{id}")
    public AdminTrip updateTrip(@RequestBody @Valid AdminTripDto adminProductDto, @PathVariable Long id)
    {
        AdminTrip build = AdminTrip.builderWithId()
                .id(id)
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
                .buildWithId();

        return adminTripService.updateTrip(build);
    }

    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable Long id)
    {
        adminTripService.deleteTrip(id);
    }

    @PostMapping("/images/upload-image")
    public ResponseEntity<UploadResponse> uploadImage(@RequestParam("image") MultipartFile image) {

        Image model = new Image();
        String name = slugifySlug(image.getOriginalFilename());
        model.setName(name);
        model.setType(image.getContentType());
        try {
            model.setData(image.getBytes());
            //imageRepository.save(image);
            imageService.saveFile(model);
            return ResponseEntity.ok(new UploadResponse(name, "Image uploaded successfully"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadResponse(name, "Error uploading image"));
        }

    }



    private String slugifySlug(String slug)
    {
        final Slugify slg = Slugify.builder().customReplacement("_", "-").build();
        return slg.slugify(slug);
    }
}
