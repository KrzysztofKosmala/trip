package pl.kosmala.shop.product.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.product.trip.model.Trip;
import pl.kosmala.shop.product.trip.repository.TripRepository;

import java.util.List;

@Service
public class TripService
{
    @Autowired
    TripRepository tripRepository;

    public Page<Trip> getAllTrips(Pageable pageable)
    {
        return tripRepository.findAll(pageable);
    }
}
