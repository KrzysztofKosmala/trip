package pl.kosmala.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.order.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>
{
}
