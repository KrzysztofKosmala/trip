package pl.kosmala.shop.homepage;

import pl.kosmala.shop.common.model.Product;

import java.util.List;

public interface HomePageStrategy
{
    public List<Product> generateHomePageProducts();
}
