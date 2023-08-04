package pl.kosmala.shop.homepage.service.displayProduct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.homepage.model.HomePageSettings;
import pl.kosmala.shop.homepage.repository.HomePageSettingsRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomePageProductStrategyService
{

    private final HomePageSettingsRepository homePageSettingsRepository;

    private final Map<String, HomePageProductStrategy> homePageProductStrategyMap;

    public HomePageProductStrategy getInstance()
    {
        HomePageSettings homePageSettings = homePageSettingsRepository.findById(1L).orElseThrow();

        if(homePageProductStrategyMap.containsKey(homePageSettings.getProductStrategy().name()))
            return homePageProductStrategyMap.get(homePageSettings.getProductStrategy().name());

        log.info("There is no such strategy setting BASIC");
        return homePageProductStrategyMap.get("BASIC");
    }
}
