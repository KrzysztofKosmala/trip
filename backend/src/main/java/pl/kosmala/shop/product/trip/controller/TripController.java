package pl.kosmala.shop.product.trip.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.product.trip.model.Trip;
import pl.kosmala.shop.product.trip.service.TripService;

import java.util.List;


@RestController
@RequestMapping("api/v1/trips")
@AllArgsConstructor
public class TripController
{
    final TripService tripService;

    @GetMapping
    public Page<Trip> getTrips(@PageableDefault(size = 30) Pageable pageable)
    {
        return tripService.getAllTrips(pageable);
    }
}
