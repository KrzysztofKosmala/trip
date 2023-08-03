package pl.kosmala.shop.admin.homepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.admin.homepage.model.HomePageSettings;
import pl.kosmala.shop.admin.homepage.model.dto.AdminHomePageInitData;
import pl.kosmala.shop.admin.homepage.model.dto.HomePageSettingsDto;
import pl.kosmala.shop.admin.homepage.service.AdminHomePageService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/homePage")
@RequiredArgsConstructor
public class AdminHomePageController
{
    private final AdminHomePageService adminHomePageService;

    @GetMapping("/initData")
    public AdminHomePageInitData getInitData()
    {
        return adminHomePageService.getInitData();
    }

    @GetMapping("/getSettings")
    public HomePageSettingsDto getHomePageSettings()
    {
        return adminHomePageService.getHomePageSettings();
    }

    @PatchMapping()
    public void patchHomePageSettings(@RequestBody HomePageSettingsDto homePageSettings)
    {
       adminHomePageService.patchHomePageSettings(homePageSettings);
    }

}
