package pl.kosmala.shop.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.trip.model.Trip;

public interface AdminTripRepository extends JpaRepository<AdminTrip, Long>
{
}
