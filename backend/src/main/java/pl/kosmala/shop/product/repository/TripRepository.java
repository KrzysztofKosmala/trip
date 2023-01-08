package pl.kosmala.shop.product.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kosmala.shop.product.model.Trip;

import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long>
{

    @Override
    List<Trip> findAll();
}
