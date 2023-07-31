package pl.kosmala.shop.homepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.homepage.dto.HomePageDto;
import pl.kosmala.shop.homepage.service.HomePageService;

import java.util.List;

@RestController
@RequestMapping("api/v1/homePage")
@RequiredArgsConstructor
public class HomePageController
{
    private final HomePageService homePageService;

    @GetMapping
    public HomePageDto getHomePage()
    {
        List<Product> products = homePageService.getProducts();
        return new HomePageDto(products);
    }
}
