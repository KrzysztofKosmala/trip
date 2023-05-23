package pl.kosmala.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.common.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long>
{
    List<AdminOrder> findAllByPlaceDateIsBetweenAndOrderStatus(LocalDateTime from, LocalDateTime to, OrderStatus orderStatus);
}
