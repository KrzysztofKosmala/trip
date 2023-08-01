package pl.kosmala.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.kosmala.shop.admin.trip.dto.AdminTripDto;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.fakeData.AdminTripDtoGenerator;
import pl.kosmala.shop.fakeData.ImageGenerator;
import pl.kosmala.shop.fakeData.OrderGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.util.*;

@DataJpaTest
@Transactional
@ActiveProfiles("test")
public class RelationTest
{
    private final int HOW_MANY_TRIPS = 10;
    private final int HOW_MANY_IMAGES = 30;
    @Autowired
    private AdminTripRepository adminTripRepository;
    protected AdminTripService adminTripService;
    protected Random random = new Random();

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    ImageRepository imageRepository;
    protected List<AdminTrip> tripsFromDb;
    protected List<Image> imagesFromDb;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup()
    {

        imageRepository.deleteAll();
        adminTripRepository.deleteAll();
        adminTripService = new AdminTripService(adminTripRepository, imageRepository);
        //dodać kilka tripów
        ProductGenerator productGenerator = new ProductGenerator();
        List<AdminTrip> trips = productGenerator.adminTrips(HOW_MANY_TRIPS);
        adminTripRepository.saveAll(trips);
        //dodać kilka images
        ImageGenerator imageGenerator = new ImageGenerator();
        List<Image> images = imageGenerator.generateImages(HOW_MANY_IMAGES);
        imageRepository.saveAll(images);
        //połączyć ze sobą
        List<Image> allImages = imageRepository.findAll();
        List<AdminTrip> allTrips = adminTripRepository.findAll();

        allTrips.forEach(trip ->
                {

                    Set<Integer> ints = new HashSet<>();

                    while(ints.size() < 2)
                    {
                        ints.add(random.nextInt(allImages.size()));
                    }
                    ints.forEach(number -> {
                        Image image = allImages.get(number);
                        trip.addImage(image);
                        imageRepository.save(image);
                    });

                    adminTripRepository.save(trip);
                }
        );
        OrderGenerator orderGenerator = new OrderGenerator();
        imagesFromDb = imageRepository.findAll();
        tripsFromDb = adminTripRepository.findAll();
    }
    @Test
    void shouldCheckIfSetupWorksProperly()
    {
        // Sprawdzenie czy dane testowe zostały dodane do bazy danych
        Assertions.assertEquals(HOW_MANY_TRIPS, tripsFromDb.size());
        Assertions.assertEquals(HOW_MANY_IMAGES, imagesFromDb.size());

        // Sprawdzenie czy każda wycieczka ma przypisane losowe zdjęcie
        for (AdminTrip trip : tripsFromDb)
        {
            Set<Image> tripImages = trip.getImages();
            List<Order> orders = trip.getOrders();
            Assertions.assertEquals(2, tripImages.size());
            Assertions.assertEquals(10, orders.size());
        }

    }

    @Test
    void shouldDeleteOnlyProduct()
    {
        int imageIndex = random.nextInt(tripsFromDb.size());
        Long id = tripsFromDb.get(imageIndex).getId();
        long amountOfImagesBeforeDeleting = imageRepository.count();
        long amountOfTripsBeforeDeleting = adminTripRepository.count();
        Assertions.assertEquals(true, tripRepository.existsById(id));
        long amountOfOrdersBeforeDeleting = orderRepository.count();

        AdminTrip product = adminTripRepository.findById(id).orElseThrow();


        List<Long> orderIds = product.getOrders().stream().map(order -> order.getId()).toList();

        adminTripService.deleteTrip(id);

        List<Order> idsOfOrdersThatShouldBeDetachFromProduct = orderRepository.findAllById(orderIds);


        idsOfOrdersThatShouldBeDetachFromProduct.forEach( order -> Assertions.assertEquals(null,order.getProduct()));
        Assertions.assertEquals(amountOfOrdersBeforeDeleting, orderRepository.count());
        Assertions.assertEquals(amountOfImagesBeforeDeleting, imageRepository.count());
        Assertions.assertEquals(amountOfTripsBeforeDeleting - 1, adminTripRepository.count());
        Assertions.assertEquals(false, tripRepository.existsById(id));
    }

    @Test
    void shouldUpdateAdminTrip()
    {
        AdminTripDtoGenerator adminTripDtoGenerator = new AdminTripDtoGenerator();
        AdminTripDto adminTripDto = adminTripDtoGenerator.generateAdminTripDtp(imagesFromDb);
        List<Long> ids = tripsFromDb.stream().map(trip -> trip.getId()).toList();
        int productToFetch = random.nextInt(ids.size());
        Long idOfProductToFetch = ids.get(productToFetch);


        adminTripService.updateTrip(adminTripDto, idOfProductToFetch);

        AdminTrip updatedTrip = adminTripRepository.findById(idOfProductToFetch).orElseThrow();

        Assertions.assertEquals(adminTripDto.getApartment(), updatedTrip.getApartment());
        Assertions.assertEquals(adminTripDto.getFood(), updatedTrip.getFood());
        Assertions.assertEquals(adminTripDto.getDesc(), updatedTrip.getDesc());
        Assertions.assertEquals(adminTripDto.getName(), updatedTrip.getName());
        Assertions.assertEquals(adminTripDto.getSlug(), updatedTrip.getSlug());
        Assertions.assertEquals(adminTripDto.getFullDesc(), updatedTrip.getFullDesc());
        Assertions.assertEquals(Arrays.stream(adminTripDto.getImages()).count(), updatedTrip.getImages().size());

        List<Long> idsFromUpdatedTrip = updatedTrip.getImages().stream().map(Image::getId).sorted().toList();
        List<Long> idsFromAdminTripDto = Arrays.stream(adminTripDto.getImages()).map(Image::getId).sorted().toList();
        Assertions.assertEquals(idsFromAdminTripDto, idsFromUpdatedTrip);
    }

}

