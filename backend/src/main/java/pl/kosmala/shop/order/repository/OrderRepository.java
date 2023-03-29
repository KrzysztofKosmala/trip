package pl.kosmala.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{

}
