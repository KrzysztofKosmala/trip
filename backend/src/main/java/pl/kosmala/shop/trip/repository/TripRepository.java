package pl.kosmala.shop.trip.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.common.model.TripDestination;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long>
{
    Optional<Trip> findTripBySlug(String slug);

    @Query("SELECT t FROM Trip t WHERE (:destination IS NULL OR t.destination = :destination) "
            + "AND (:slopNearby IS NULL OR t.slopNearby = :slopNearby) "
            + "AND (:apartment IS NULL OR t.apartment = :apartment) "
            + "AND (:house IS NULL OR t.house = :house) "
            + "AND (:wifi IS NULL OR t.wifi = :wifi) "
            + "AND (:food IS NULL OR t.food = :food) "
            + "AND (:spa IS NULL OR t.spa = :spa)")
    Page<Trip> findByFilter(
            Pageable pageable,
            @Param("destination") TripDestination destination,
            @Param("slopNearby") Boolean slopNearby,
            @Param("apartment") Boolean apartment,
            @Param("house") Boolean house,
            @Param("wifi") Boolean wifi,
            @Param("food") Boolean food,
            @Param("spa") Boolean spa
    );
}
