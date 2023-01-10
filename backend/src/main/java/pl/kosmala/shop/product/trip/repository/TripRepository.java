package pl.kosmala.shop.product.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.kosmala.shop.product.trip.model.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long>
{
}
