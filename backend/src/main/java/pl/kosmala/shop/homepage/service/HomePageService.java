package pl.kosmala.shop.homepage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.homepage.service.displayProduct.HomePageProductStrategyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomePageService
{
    private final HomePageProductStrategyService strategy;

    public List<Product> getProducts()
            {
                return strategy.getInstance().generateHomePageProducts();
            }

}
