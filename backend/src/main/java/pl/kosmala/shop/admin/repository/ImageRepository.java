package pl.kosmala.shop.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosmala.shop.common.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {}
