package pl.kosmala.shop.admin.homepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.admin.homepage.model.HomePageSettings;
import pl.kosmala.shop.admin.homepage.model.dto.AdminHomePageInitData;
import pl.kosmala.shop.admin.homepage.service.AdminHomePageService;

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
    public HomePageSettings getHomePageSettings()
    {
        return adminHomePageService.getHomePageSettings();
    }

}
