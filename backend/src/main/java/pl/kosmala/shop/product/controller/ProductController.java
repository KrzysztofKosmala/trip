package pl.kosmala.shop.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.product.model.Product;
import pl.kosmala.shop.product.model.Trip;
import pl.kosmala.shop.product.repository.TripRepository;
import pl.kosmala.shop.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
public class ProductController
{
    final ProductService productService;

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getTrips()
    {
        List<Trip> all = productService.getAllTrips();
        return ResponseEntity.ok(all);
    }
}
