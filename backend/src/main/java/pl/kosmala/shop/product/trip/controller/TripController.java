package pl.kosmala.shop.product.trip.controller;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.product.trip.dto.TripDto;
import pl.kosmala.shop.product.trip.model.Trip;
import pl.kosmala.shop.product.trip.model.TripDestination;
import pl.kosmala.shop.product.trip.service.TripService;

import javax.validation.constraints.Pattern;
import java.util.List;


@RestController
@RequestMapping("api/v1/trips")
@AllArgsConstructor
@Validated
public class TripController
{
    final TripService tripService;

    @GetMapping
    public Page<Trip> getTrips(@PageableDefault(size = 30) Pageable pageable)
    {
        return tripService.getAllTrips(pageable);
    }

    @GetMapping("/byFilter")
    public Page<Trip> getTripsFilter
            (
                    @PageableDefault(size = 30) Pageable pageable,
                    @RequestParam(required = false) TripDestination destination,
                    @RequestParam(required = false) Boolean slopNearby,
                    @RequestParam(required = false) Boolean apartment,
                    @RequestParam(required = false) Boolean house,
                    @RequestParam(required = false) Boolean wifi,
                    @RequestParam(required = false) Boolean food,
                    @RequestParam(required = false) Boolean spa
            )
    {

        return tripService.getTripsByFilter(pageable, destination, slopNearby, apartment, house, wifi, food, spa);
    }

    @GetMapping("/{slug}")
    public TripDto getTripBySlug(@PathVariable @Pattern(regexp = "[a-z0-9\\-]+") @Length(max = 255) String slug)
    {
        return tripService.getTripBySlug(slug);
    }

}
