package pl.kosmala.shop.product.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.product.trip.dto.TripDto;
import pl.kosmala.shop.product.trip.model.Trip;
import pl.kosmala.shop.product.trip.model.TripDestination;
import pl.kosmala.shop.product.trip.repository.TripRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TripService
{
    @Autowired
    TripRepository tripRepository;


    public Page<Trip> getAllTrips(Pageable pageable)
    {
        return tripRepository.findAll(pageable);
    }

    public TripDto getTripBySlug(String slug)
    {
        Trip tripBySlug = tripRepository.findTripBySlug(slug).orElseThrow();
        return TripDto.builder()
                        .name(tripBySlug.getName())
                        .destination(tripBySlug.getDestination())
                        .desc(tripBySlug.getDesc())
                        .basePrice(tripBySlug.getBasePrice())
                        .currency(tripBySlug.getCurrency())
                        .slug(tripBySlug.getSlug())
                        .fullDesc(tripBySlug.getFullDesc())
                        .build();
    }

    public Page<Trip> getTripsByFilter(Pageable pageable, TripDestination destination, Boolean slopNearby, Boolean apartment, Boolean house, Boolean wifi, Boolean food, Boolean spa) {
        return tripRepository.findByFilter(pageable, destination, slopNearby, apartment, house, wifi, food, spa);
    }
}
