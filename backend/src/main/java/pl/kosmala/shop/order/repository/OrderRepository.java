package pl.kosmala.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.order.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>
{

    List<Order> findByUserId(Long id);


}
