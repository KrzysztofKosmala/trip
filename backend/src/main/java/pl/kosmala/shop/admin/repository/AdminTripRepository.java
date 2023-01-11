package pl.kosmala.shop.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.admin.model.AdminTrip;

public interface AdminTripRepository extends JpaRepository<AdminTrip, Long>
{
}
