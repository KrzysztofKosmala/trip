package pl.kosmala.shop;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.kosmala.shop.admin.trip.dto.AdminTripDto;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.common.utils.SlugifyUtils;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.PaymentType;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.entity.types.Role;
import pl.kosmala.shop.common.user.repository.UserRepository;
import pl.kosmala.shop.security.payload.RegisterRequest;
import pl.kosmala.shop.security.service.AuthenticationService;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.ProductRepository;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

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
	CommandLineRunner commandLineRunner(AdminTripService adminTripService, AuthenticationService authenticationService, ImageRepository imageRepository, AdminTripRepository adminTripRepository, TripRepository tripRepository, OrderRepository orderRepository, PaymentRepository paymentRepository, UserRepository userRepository)
	{
		return args ->
		{

/*			RegisterRequest request1 = RegisterRequest.builder()
					.firstname("user1")
					.lastname("user1")
					.role(Role.ROLE_CUSTOMER)
					.email("user1@pl.pl")
					.password("password")
					.build();
			RegisterRequest request2 = RegisterRequest.builder()
					.firstname("user2")
					.lastname("user2")
					.role(Role.ROLE_CUSTOMER)
					.email("user2@pl.pl")
					.password("password")
					.build();
			RegisterRequest request3 = RegisterRequest.builder()
					.firstname("user3")
					.lastname("user3")
					.role(Role.ROLE_CUSTOMER)
					.email("user3@pl.pl")
					.password("password")
					.build();
			RegisterRequest request4 = RegisterRequest.builder()
					.firstname("user4")
					.lastname("user4")
					.role(Role.ROLE_CUSTOMER)
					.email("user4@pl.pl")
					.password("password")
					.build();

			authenticationService.register(request1);
			authenticationService.register(request2);
			authenticationService.register(request3);
			authenticationService.register(request4);
			List<Image> images = generateImages(10);
			imageRepository.saveAll(images);

			List<Image> imagesFromRepo = imageRepository.findAll();
			//trips
			for(int i = 0; i < 5; i++)
			{
				adminTripService.createTrip(generateAdminTripDtp(imagesFromRepo));
			}

			RegisterRequest request5 = RegisterRequest.builder()
					.firstname("user1")
					.lastname("user1")
					.role(Role.ROLE_ADMIN)
					.email("kosmi@pl.pl")
					.password("password")
					.build();
			authenticationService.register(request5);*/
		};
	}
	public AdminTripDto generateAdminTripDtp(List<Image> allImages)
	{
		List<Image> copyOf = new ArrayList<Image>(allImages);

		AdminTripDto trip = new AdminTripDto();

		trip.setDestination(TripDestination.AU);
		trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
		String name = faker.name().name();
		trip.setName(name);
		trip.setDesc(faker.lorem().sentence( 30));
		trip.setSlug(SlugifyUtils.slugifySlug(name));
		trip.setFullDesc(faker.lorem().sentence(20));
		trip.setCurrency(ProductCurrency.PLN);
		trip.setHouse(faker.bool().bool());
		trip.setSpa(faker.bool().bool());
		trip.setFood(faker.bool().bool());
		trip.setSlopNearby(faker.bool().bool());
		trip.setWifi(faker.bool().bool());
		trip.setApartment(faker.bool().bool());


		int howMany = random.nextInt(1,4);

		Image[] randomIds = new Image[howMany];
		for (int i = 0; i < howMany; i++)
		{
			int i1 = random.nextInt(copyOf.size());
			Image image = new Image();
			image.setId(copyOf.get(i1).getId());
			randomIds[i] = image;
			copyOf.remove(copyOf.get(i1));
		}
		trip.setImages(randomIds);
		return trip;
	}
	@Value("${FETCH_IMAGE_URL}")
	private String fetchImage;

	public Image generateImage()
	{
		Image model = new Image();
		String name = slugifySlug(faker.animal().name());

		model.setThumbImage(fetchImage+name);
		model.setName(name);
		model.setDesc(faker.lorem().sentence());
		model.setLocation(TripDestination.AU);
		model.setType(faker.artist().name());

		return model;

	}


	public List<Image> generateImages(int howMany)
	{
		List<Image> images = new ArrayList<>();
		for (int i =0; i<howMany; i++)
		{
			images.add(this.generateImage());
		}

		return images;
	}

}
