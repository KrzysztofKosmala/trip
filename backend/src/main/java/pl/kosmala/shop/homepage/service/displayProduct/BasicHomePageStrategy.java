package pl.kosmala.shop.homepage.service.displayProduct;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.trip.repository.ProductRepository;

import java.util.List;

@Service("BASIC")
@RequiredArgsConstructor
public class BasicHomePageStrategy implements HomePageProductStrategy
{

    private final ProductRepository productRepository;
    @Override
    public List<Product> generateHomePageProducts()
    {
        return productRepository.findTop5BySalePriceIsNotNull();
    }
}
