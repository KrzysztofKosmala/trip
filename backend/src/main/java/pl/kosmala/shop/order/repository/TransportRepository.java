package pl.kosmala.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.order.model.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long>
{
}
