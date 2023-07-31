package pl.kosmala.shop.homepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.homepage.HomePageStrategy;

import java.util.List;

@Service
public class HomePageService
{
    private HomePageStrategy strategy;

    @Autowired
    public HomePageService(@Qualifier("simple") HomePageStrategy strategy)
        {
            this.strategy = strategy;
        }

        public List<Product> getProducts()
            {
                //logika wybrania strategii
                return strategy.generateHomePageProducts();
            }

}
