package pl.kosmala.shop.common.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kosmala.shop.common.model.Image;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.trip.model.Trip;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long>
{
    @Query("SELECT t FROM Image t  WHERE t.destination = :destination")
    Page<Image> findAllByDestination(Pageable pageable, @Param("destination") TripDestination destination);
}
