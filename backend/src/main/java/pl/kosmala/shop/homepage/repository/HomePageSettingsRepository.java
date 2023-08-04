package pl.kosmala.shop.homepage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.admin.homepage.model.HomePageSettings;

public interface HomePageSettingsRepository extends JpaRepository<HomePageSettings, Long>
{
}
