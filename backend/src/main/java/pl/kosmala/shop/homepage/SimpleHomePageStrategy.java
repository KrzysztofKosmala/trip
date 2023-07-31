package pl.kosmala.shop.homepage;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.trip.repository.ProductRepository;

import java.util.List;

@Component("simple")
@RequiredArgsConstructor
public class SimpleHomePageStrategy implements HomePageStrategy
{

    private final ProductRepository productRepository;
    @Override
    public List<Product> generateHomePageProducts()
    {
        return productRepository.findTop5BySalePriceIsNotNull();
    }
}
