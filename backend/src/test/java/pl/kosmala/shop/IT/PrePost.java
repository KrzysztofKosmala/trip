package pl.kosmala.shop.IT;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.admin.order.repository.AdminOrderRepository;
import pl.kosmala.shop.admin.room.repository.RoomRepository;
import pl.kosmala.shop.admin.room.service.RoomService;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.repository.UserRepository;
import pl.kosmala.shop.fakeData.*;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.order.service.OrderService;
import pl.kosmala.shop.trip.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class PrePost
{
    @Autowired
    protected AdminTripRepository adminTripRepository;
    @Autowired
    protected ImageRepository imageRepository;

    protected AdminTripService adminTripService;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected PaymentRepository paymentRepository;
    @Autowired
    protected RoomService roomService;
    @Autowired
    protected AdminOrderRepository adminOrderRepository;
    @Autowired
    protected RoomRepository roomRepository;

    protected static final int HOW_MANY_USERS = 10;
    protected static final int HOW_MANY_IMAGES = 10;
    protected static final int HOW_MANY_TRIPS = 5;
    @Autowired
    protected OrderService orderService;
    protected OrderDtoGenerator orderDtoGenerator = new OrderDtoGenerator();
    protected Random random = new Random();
    protected AdminTripDtoGenerator adminTripDtoGenerator = new AdminTripDtoGenerator();
    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.1")
            .withDatabaseName("test")
            .withPassword("test")
            .withUsername("test")
            .withConnectTimeoutSeconds(60);

    @DynamicPropertySource
    public static void containerConfig(DynamicPropertyRegistry  registry)
    {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @BeforeEach
    public void setUpDatabase()
    {
        //images
        ImageGenerator imageGenerator = new ImageGenerator();
        List<Image> images = imageGenerator.generateImages(HOW_MANY_IMAGES);
        imageRepository.saveAll(images);
        List<Image> imagesFromRepo = imageRepository.findAll();

        //trips
        adminTripService = new AdminTripService(adminTripRepository, imageRepository);
        for(int i = 0; i < HOW_MANY_TRIPS; i++)
        {
            adminTripService.createTrip(adminTripDtoGenerator.generateAdminTripDtp(imagesFromRepo));
        }
        //users
        UserGenerator userGenerator = new UserGenerator();
        List<User> users = userGenerator.generateUsers(HOW_MANY_USERS);

        List<User> saved = userRepository.saveAll(users);
        Map<Long, String> idEmail = saved.stream()
                .collect(Collectors.toMap(User::getId, User::getEmail));
        //orders
        List<AdminTrip> tripsForPlacingOrders = adminTripRepository.findAll();
        List<User> usersForPlacingOrders = userRepository.findAll();
        makeOrders(usersForPlacingOrders, tripsForPlacingOrders);
        List<AdminOrder> adminOrdersFromRepo = adminOrderRepository.findAll();
        adminOrdersFromRepo.forEach(adminOrder -> {
            adminOrder.setOrderStatus(OrderStatus.PAID);
        });
        adminOrderRepository.saveAll(adminOrdersFromRepo);
        //rooms

        //assignRoomMates(idEmail);
    }

    private void makeOrders(List<User> users, List<AdminTrip> trips)
    {
        orderService.placeTripOrder
                (
                    orderDtoGenerator.generateOrderDto
                            (
                                    trips.stream().filter(trip -> trip.getId().equals(1L)).findFirst().get(),
                                    users.stream().filter(user -> user.getId().equals(1L)).findFirst().get()
                            ),
                            users.stream().filter(user -> user.getId().equals(1L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(1L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(2L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(2L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(2L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(3L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(3L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(2L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(4L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(4L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(3L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(5L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(5L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(3L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(6L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(6L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(4L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(7L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(7L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(5L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(8L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(8L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(5L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(9L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(9L)).findFirst().get()
                );
        orderService.placeTripOrder
                (
                        orderDtoGenerator.generateOrderDto
                                (
                                        trips.stream().filter(trip -> trip.getId().equals(5L)).findFirst().get(),
                                        users.stream().filter(user -> user.getId().equals(10L)).findFirst().get()
                                ),
                        users.stream().filter(user -> user.getId().equals(10L)).findFirst().get()
                );
    }

    private void assignRoomMates(Map<Long, String> idEmail)
    {

        roomService.addRoomMates
                (
                        userRepository.findById(1L).get(),
                        Arrays.asList(idEmail.get(2L)),
                        1L
                );

        roomService.addRoomMates
                (
                        userRepository.findById(3L).get(),
                        Arrays.asList(idEmail.get(4L)),
                        2L
                );
        roomService.addRoomMates
                (
                        userRepository.findById(5L).get(),
                        Arrays.asList(idEmail.get(6L)),
                        3L
                );
        roomService.addRoomMates
                (
                        userRepository.findById(8L).get(),
                        Arrays.asList(idEmail.get(9L), idEmail.get(10L)),
                        5L
                );
    }
}
