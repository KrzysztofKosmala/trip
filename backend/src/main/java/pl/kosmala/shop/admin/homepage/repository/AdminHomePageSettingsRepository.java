package pl.kosmala.shop.admin.homepage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.admin.homepage.model.HomePageSettings;

public interface AdminHomePageSettingsRepository extends JpaRepository<HomePageSettings, Long>
{

}
