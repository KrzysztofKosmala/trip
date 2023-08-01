package pl.kosmala.shop.admin.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kosmala.shop.admin.trip.model.AdminTrip;

import java.util.Optional;

public interface AdminTripRepository extends JpaRepository<AdminTrip, Long>
{
    @Override
    @Query("SELECT t FROM AdminTrip t WHERE t.id = :id")
    Optional<AdminTrip> findById(@Param("id") Long id);

    @Query("SELECT DISTINCT i FROM AdminTrip i LEFT JOIN FETCH i.images WHERE i.id = :id")
    Optional<AdminTrip>  findByIdWithImages(@Param("id") Long id);
    boolean existsByName(String name);
}
