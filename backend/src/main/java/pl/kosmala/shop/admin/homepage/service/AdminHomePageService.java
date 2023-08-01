package pl.kosmala.shop.admin.homepage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.homepage.model.HomePageProductStrategy;
import pl.kosmala.shop.admin.homepage.model.HomePageSettings;
import pl.kosmala.shop.admin.homepage.model.dto.AdminHomePageInitData;
import pl.kosmala.shop.admin.homepage.repository.AdminHomePageSettingsRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminHomePageService
{
    private final AdminHomePageSettingsRepository adminHomePageSettingsRepository;
    public AdminHomePageInitData getInitData()
    {
        return new AdminHomePageInitData(createProductStrategiesMap());
    }

    /*TODO:
     *  retrieve it from repo*/
    private Map<String, String> createProductStrategiesMap() {
        Map<String, String> strategies = new HashMap<>();
        for (HomePageProductStrategy status : HomePageProductStrategy.values()) {
            strategies.put(status.name(), status.getValue());
        }
        return strategies;
    }

    public HomePageSettings getHomePageSettings()
    {
        HomePageSettings homePageSettings = adminHomePageSettingsRepository.findById(1L).orElseThrow();
        return homePageSettings;
    }
}
