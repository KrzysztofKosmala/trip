package pl.kosmala.shop;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.common.utils.SlugifyUtils;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.TripRepository;
import pl.kosmala.shop.review.repository.ReviewRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class TripApplication {
	Faker faker = new Faker();

	Random random =new Random();
	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TripRepository tripRepository, ReviewRepository reviewRepository)
	{
		return args ->
		{
			List<Trip> trips = trips(10);
			tripRepository.saveAll(trips);
		};
	}
	public Trip generateTrip()
	{
		Trip trip = new Trip();

		trip.setDestination(TripDestination.AU);
		trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
		String name = faker.name().name();
		trip.setName(name);
		trip.setDesc(faker.lorem().sentence( 30));
		trip.setSlug(SlugifyUtils.slugifySlug(name));
		trip.setFullDesc(faker.lorem().sentence(20));
		trip.setCurrency(ProductCurrency.PLN);
		trip.setHouse(faker.bool().bool());
		trip.setSpa(true);
		trip.setFood(true);
		trip.setSlopNearby(true);
		trip.setWifi(true);
		trip.setApartment(true);
		return trip;
	}

	public List<Trip> trips(int howMany)
	{
		List<Trip> trips = new ArrayList<>();
		for (int i =0; i<howMany; i++)
		{
			trips.add(this.generateTrip());
		}

		return trips;
	}
}
