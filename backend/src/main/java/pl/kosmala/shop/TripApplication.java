package pl.kosmala.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.kosmala.shop.product.fakeData.ProductGenerator;
import pl.kosmala.shop.product.model.Product;
import pl.kosmala.shop.product.model.Trip;
import pl.kosmala.shop.product.repository.ProductRepository;
import pl.kosmala.shop.product.repository.TripRepository;

import java.util.List;

@SpringBootApplication
public class TripApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TripRepository tripRepository, ProductRepository productRepository)
	{
		return args ->
		{
			ProductGenerator productGenerator = new ProductGenerator();
			List<Trip> trips = productGenerator.trips(100);
			List<Product> products = productGenerator.products(50);

			tripRepository.saveAll(trips);

		};
	}
}
