package pl.kosmala.shop.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.product.model.Trip;
import pl.kosmala.shop.product.repository.TripRepository;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    TripRepository tripRepository;

            public List<Trip> getAllTrips()
            {
                return tripRepository.findAll();
            }
}
