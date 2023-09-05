package pl.kosmala.shop.IT;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import pl.kosmala.shop.admin.trip.dto.AdminTripDto;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.fakeData.ImageGenerator;
import pl.kosmala.shop.fakeData.OrderGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.fakeData.UserGenerator;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Relation extends PrePost
{




    /*TODO:
    *  delete trip
    *  create trip
    *  update trip
    *
    * */
    @Test
    @Transactional
    void shouldAddTripBasic()
    {

        AdminTripDto adminTripDto = new AdminTripDto();
        adminTripDto.setName("name");
        adminTripDto.setDesc("description");
        adminTripDto.setBasePrice(new BigDecimal(10));
        adminTripDto.setCurrency(ProductCurrency.PLN);
        adminTripDto.setSlug("slug");
        adminTripDto.setFullDesc("full description");
        adminTripDto.setDestination(TripDestination.PL);
        adminTripDto.setSlopNearby(true);
        adminTripDto.setApartment(true);
        adminTripDto.setHouse(false);
        adminTripDto.setWifi(true);
        adminTripDto.setFood(true);
        adminTripDto.setSpa(false);
        adminTripDto.setStartDate(LocalDate.now());
        adminTripDto.setEndDate(LocalDate.now().plusDays(7));

        List<Image> imageRepositoryAll = imageRepository.findAll();

        Image[] imagesInAdminTripDto = new Image[3];
        // Tworzenie listy do przechowywania unikalnych wartości randomImageIndex
        List<Integer> uniqueIndexes = new ArrayList<>();

        // Pętla for
        for (int i = 0; i < 3; i++) {
            int randomImageIndex;
            Image image;
            do {
                randomImageIndex = random.nextInt(10);
            } while (uniqueIndexes.contains(randomImageIndex));
            uniqueIndexes.add(randomImageIndex);
            image = imageRepositoryAll.get(randomImageIndex);
            imagesInAdminTripDto[i] = image;
        }

        adminTripDto.setImages(imagesInAdminTripDto);

        long adminTripsInRepoBefore = adminTripRepository.count();

        AdminTrip trip = adminTripService.createTrip(adminTripDto);

        long adminTripsInRepoAfter = adminTripRepository.count();
        Assertions.assertEquals(adminTripDto.getName(), trip.getName());
        Assertions.assertEquals(adminTripsInRepoBefore + 1, adminTripsInRepoAfter);

        Arrays.stream(imagesInAdminTripDto).toList().forEach(image -> Assertions.assertTrue(trip.getImages().contains(image)));
    }


    @Test
    @Transactional
    @DirtiesContext
    void shouldDeleteOnlyTrip()
    {

        long ordersCount = orderRepository.count();
        long usersCount = userRepository.count();
        long imagesCount = imageRepository.count();
        long tripsCount = adminTripRepository.count();
        long roomsCount = roomRepository.count();



        long id = random.nextLong(1,HOW_MANY_TRIPS);
        while (id == 4L)
        {
            id = random.nextLong(1,HOW_MANY_TRIPS);
        }
        //Long roomsInTrip = adminTripRepository.countRoomsByAdminTripId(id);

        adminTripService.deleteTrip(id);


        Assertions.assertEquals(ordersCount, orderRepository.count());
        Assertions.assertEquals(usersCount, userRepository.count());
        Assertions.assertEquals(imagesCount, imageRepository.count());

        Assertions.assertEquals(tripsCount -1 , adminTripRepository.count());
        //Assertions.assertEquals(roomsCount - roomsInTrip , roomRepository.count());


    }

    @Test
    @Transactional
    @DirtiesContext
    void shouldUpdateTrip()
    {
        //dodać kilka images
        ImageGenerator imageGenerator = new ImageGenerator();
        List<Image> images = imageGenerator.generateImages(10);
        imageRepository.saveAll(images);

        ProductGenerator productGenerator = new ProductGenerator();
        List<AdminTrip> trips = productGenerator.adminTrips(5);
        adminTripRepository.saveAll(trips);
        List<Image> allImages = imageRepository.findAllWithProducts();
        List<AdminTrip> allTrips = adminTripRepository.findAll();

        UserGenerator userGenerator = new UserGenerator();

        List<User> users = userGenerator.generateUsers(10);

        userRepository.saveAll(users);

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
        List<? extends Order> orders = orderGenerator.generateOrders(trips, 10, users);
        orderRepository.saveAll(orders);

        long ordersCount = orderRepository.count();
        long usersCount = userRepository.count();
        long imagesCount = imageRepository.count();
        long tripsCount = adminTripRepository.count();

        AdminTripDto adminTripDto = new AdminTripDto();
        adminTripDto.setName("name");
        adminTripDto.setDesc("description");
        adminTripDto.setBasePrice(new BigDecimal(10));
        adminTripDto.setCurrency(ProductCurrency.PLN);
        adminTripDto.setSlug("slug");
        adminTripDto.setFullDesc("full description");
        adminTripDto.setDestination(TripDestination.PL);
        adminTripDto.setSlopNearby(true);
        adminTripDto.setApartment(true);
        adminTripDto.setHouse(false);
        adminTripDto.setWifi(true);
        adminTripDto.setFood(true);
        adminTripDto.setSpa(false);
        adminTripDto.setStartDate(LocalDate.now());
        adminTripDto.setEndDate(LocalDate.now().plusDays(7));


        Image[] imagesInAdminTripDto = new Image[3];
        // Tworzenie listy do przechowywania unikalnych wartości randomImageIndex
        List<Integer> uniqueIndexes = new ArrayList<>();

        // Pętla for
        for (int i = 0; i < 3; i++) {
            int randomImageIndex;
            Image image;
            do {
                randomImageIndex = random.nextInt(10);
            } while (uniqueIndexes.contains(randomImageIndex));
            uniqueIndexes.add(randomImageIndex);
            image = allImages.get(randomImageIndex);
            imagesInAdminTripDto[i] = image;
        }

        adminTripDto.setImages(imagesInAdminTripDto);


        adminTripService = new AdminTripService(adminTripRepository, imageRepository);

        long id = random.nextLong(1, 5);
        adminTripService.updateTrip(adminTripDto, id);

        Optional<AdminTrip> byIdWithImages = adminTripRepository.findByIdWithImages(id);
        byIdWithImages.isPresent();

        Assertions.assertEquals(adminTripDto.getName(), byIdWithImages.get().getName());

        Set<Image> images1 = byIdWithImages.get().getImages();

        for(int i = 0; i<adminTripDto.getImages().length; i++)
        {
            Assertions.assertTrue(images1.contains(adminTripDto.getImages()[i]));
        }




    }

}
