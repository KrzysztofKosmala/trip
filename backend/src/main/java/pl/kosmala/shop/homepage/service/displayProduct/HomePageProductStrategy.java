package pl.kosmala.shop.homepage.service.displayProduct;

import pl.kosmala.shop.common.model.Product;

import java.util.List;

public interface HomePageProductStrategy
{
    public List<Product> generateHomePageProducts();
}
