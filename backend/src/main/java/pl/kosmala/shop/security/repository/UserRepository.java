package pl.kosmala.shop.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.security.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
    Optional<User> findByHash(String hash);

}
