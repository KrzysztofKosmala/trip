package pl.kosmala.shop.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.service.AdminTripService;

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
    public AdminTrip getTrip(@RequestBody AdminTripDto adminProductDto)
    {
        return adminTripService.createTrip(AdminTrip.builder()
                .name(adminProductDto.getName())
                .currency(adminProductDto.getCurrency())
                .basePrice(adminProductDto.getBasePrice())
                .desc(adminProductDto.getDesc())
                .destination(adminProductDto.getDestination())
                .build()
        );
    }

    @PutMapping("/trips/{id}")
    public AdminTrip updateTrip(@RequestBody AdminTripDto adminProductDto, @PathVariable Long id)
    {
        AdminTrip build = AdminTrip.builderWithId()
                .id(id)
                .name(adminProductDto.getName())
                .currency(adminProductDto.getCurrency())
                .basePrice(adminProductDto.getBasePrice())
                .desc(adminProductDto.getDesc())
                .category(adminProductDto.getCategory())
                .destination(adminProductDto.getDestination())
                .buildWithId();

        return adminTripService.updateTrip(build);
    }
}
