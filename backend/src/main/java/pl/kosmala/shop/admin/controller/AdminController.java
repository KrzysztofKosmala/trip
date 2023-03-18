package pl.kosmala.shop.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.admin.service.AdminTripService;
import pl.kosmala.shop.common.image.service.ImageService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminTripService adminTripService;

    @GetMapping("/trips")

    public Page<AdminTrip> getTrips(@PageableDefault(size = 30) Pageable pageable) { return adminTripService.getAllAdminTrips(pageable); }

    @GetMapping("/trips/{id}")
    public AdminTrip getTrip(@PathVariable Long id)
    {
        return adminTripService.getProduct(id);
    }

    @PostMapping("/trips")
    public AdminTrip addTrip(@RequestBody @Valid AdminTripDto adminProductDto) { return adminTripService.createTrip(adminProductDto); }

    @PutMapping("/trips/{id}")
    public AdminTrip updateTrip(@RequestBody @Valid AdminTripDto adminProductDto, @PathVariable Long id)
    { return adminTripService.updateTrip(adminProductDto, id); }

    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable Long id)
    {
        adminTripService.deleteTrip(id);
    }
}
