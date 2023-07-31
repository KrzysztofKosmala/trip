package pl.kosmala.shop.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kosmala.shop.common.model.Product;

import java.util.List;
import java.util.Optional;

@Deprecated
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    Optional<Product> findBySlug(String slug);

    List<Product> findTop5BySalePriceIsNotNull();
}
