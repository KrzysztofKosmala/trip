package pl.kosmala.shop.review.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.common.model.Review;


public interface ReviewRepository extends JpaRepository<Review, Long>
{
}
