package pl.kosmala.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.TripRepository;
import pl.kosmala.shop.common.model.Review;
import pl.kosmala.shop.review.repository.ReviewRepository;


import java.util.List;

@SpringBootApplication
public class TripApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TripRepository tripRepository, ReviewRepository reviewRepository)
	{
		return args ->
		{

		};
	}

}
