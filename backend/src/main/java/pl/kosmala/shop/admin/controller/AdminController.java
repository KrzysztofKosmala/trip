package pl.kosmala.shop.admin.controller;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.service.AdminTripService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminTripService adminTripService;

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
                .slug(adminProductDto.getSlug())
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
                .category(adminProductDto.getCategory())
                .destination(adminProductDto.getDestination())
                .slug(slugifySlug(adminProductDto.getSlug()))
                .buildWithId();

        return adminTripService.updateTrip(build);
    }

    private String slugifySlug(String slug)
    {
        final Slugify slg = Slugify.builder().customReplacement("_", "-").build();
        return slg.slugify(slug);
    }

    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable Long id)
    {
        adminTripService.deleteTrip(id);
    }
}
