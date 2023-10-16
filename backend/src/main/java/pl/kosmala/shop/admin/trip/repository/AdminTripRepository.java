package pl.kosmala.shop.admin.trip.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("SELECT t FROM AdminTrip t WHERE t.slug = :slug")
    Optional<AdminTrip> findAdminTripBySlug(@Param("slug")String slug);
/*    @Query("SELECT t FROM AdminTrip t WHERE t.slug = :slug")
    Optional<AdminTrip> findBySlug(@Param("slug") String slug);*/
    @Query("SELECT DISTINCT i FROM AdminTrip i LEFT JOIN FETCH i.images WHERE i.id = :id")
    Optional<AdminTrip>  findByIdWithImages(@Param("id") Long id);
    @Query("SELECT COUNT(r) FROM Room r WHERE r.trip.id = :tripId")
    Long countRoomsByAdminTripId(@Param("tripId") Long tripId);
    @Query("SELECT t FROM Trip t WHERE (t.isActive = true or t.isActive = null ) ")
    Page<AdminTrip> findAllActive(Pageable pageable);
    boolean existsByName(String name);
}
