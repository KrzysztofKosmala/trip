package pl.kosmala.shop.product.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.kosmala.shop.product.trip.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long>
{
    Optional<Trip> findTripBySlug(String slug);
}
