package pl.kosmala.shop;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.common.utils.SlugifyUtils;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.OrderStatus;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.PaymentType;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.security.repository.UserRepository;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.ProductRepository;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

@SpringBootApplication
public class TripApplication {
	Faker faker = new Faker();

	Random random =new Random();
	private final ProductRepository productRepository;

	public TripApplication(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TripRepository tripRepository, OrderRepository orderRepository, PaymentRepository paymentRepository, UserRepository userRepository)
	{
		return args ->
		{
/*
			User user = User.builder()
					.email("kkosmi@pl.pl")
					.firstname("krzys")
					.role(Role.ROLE_ADMIN)
					.password(new BCryptPasswordEncoder().encode("password"))
					.lastname("kosmala")
					.build();

			userRepository.save(user);*/
			List<Trip> trips = trips(10);
			tripRepository.saveAll(trips);

			Payment payment = new Payment();
			payment.setType(PaymentType.BANK_TRANSFER);
			payment.setDefaultPayment(true);
			payment.setNote("default payment");
			payment.setName("Bank transfer");
			paymentRepository.save(payment);

			List<Trip> all = tripRepository.findAll();



		for (int i = 0; i < 20; i++) {
				Product trip = productRepository.findById(new Random().nextLong(1,10)).orElseThrow();
				Payment payment1 = paymentRepository.findById(1L).orElseThrow();
				Order<Trip> order = generateOrder(payment1, trip);
				orderRepository.save(order);
				productRepository.save(trip);
				paymentRepository.save(payment1);
			};
		};
	}

	public Order generateOrder(Payment payment, Product trip) {
		Random random = new Random();
		int year = 2023; // rok, w którym chcesz wygenerować datę
		Month month = Month.MAY; // miesiąc, w którym chcesz wygenerować datę
		int day = random.nextInt(1,10); // losowy dzień w miesiącu
		int hour = random.nextInt(24); // losowa godzina od 0 do 23
		int minute = random.nextInt(60); // losowa minuta od 0 do 59
		int second = random.nextInt(60); // losowa sekunda od 0 do 59
		LocalDateTime randomDate = LocalDateTime.of(year, month, day, hour, minute, second);

		return Order.builder()
				.phone(faker.phoneNumber().cellPhone())
				.city(faker.address().city())
				.email(faker.internet().emailAddress())
				.street(faker.address().streetName())
				.grossValue(trip.getBasePrice())
				.placeDate(randomDate)
				.product(trip)
				.payment(payment)
				.orderStatus(OrderStatus.NEW)
				.zipcode(faker.address().zipCode())
				.firstname(faker.name().firstName())
				.lastname(faker.name().lastName())
				.build();
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
