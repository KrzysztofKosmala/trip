package pl.kosmala.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kosmala.shop.order.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>
{

    List<Order> findByUserId(Long id);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Order o WHERE o.user.email = :email AND o.product.slug = :slug AND o.orderStatus = 'PAID'")
    boolean hasUserPaidForProduct(@Param("email") String email, @Param("slug") String slug);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Order o WHERE o.user.email = :email AND o.product.slug = :slug")
    boolean hasUserOrderedProduct(@Param("email") String email, @Param("slug") String slug);
}
