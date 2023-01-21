package pl.kosmala.shop.product.trip.controller;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.product.trip.dto.TripDto;
import pl.kosmala.shop.product.trip.model.Trip;
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

    @GetMapping("/{slug}")
    public TripDto getTripBySlug(@PathVariable @Pattern(regexp = "[a-z0-9\\-]+") @Length(max = 255) String slug)
    {
        return tripService.getTripBySlug(slug);
    }

}
