package pl.kosmala.shop.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.fakeData.OrderGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.service.OrderService;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.ProductRepository;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.util.List;
import java.util.Random;

@DataJpaTest
@Transactional
@ActiveProfiles("test")
public class RelationTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TripRepository tripRepository;

    private OrderService orderService;
    private Random random = new Random();
    private final int HOW_MANY_PRODUCTS = 10;
    private final int HOW_MANY_ORDERS = 5;
    private final int PRODUCTS_PER_ORDER = 3;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
        orderService = new OrderService(orderRepository, productRepository);

        // Add products
        ProductGenerator productGenerator = new ProductGenerator();
        List<Trip> trips = productGenerator.trips(10);
        productRepository.saveAll(trips);

        // Add orders
        OrderGenerator orderGenerator = new OrderGenerator();
        List<? extends Order> orders = orderGenerator.generateOrders(trips, 10);
        orderRepository.saveAll(orders);
    }

    @Test
    void test(){

        List<Product> allProducts = productRepository.findAll();

        long amoutOfOrders = orderRepository.count();
        long size = productRepository.count();
        int i = random.nextInt(allProducts.size());
        Product product = allProducts.get(i);

        product.detouchAllOrders();
        productRepository.deleteById(product.getId());


        Assertions.assertEquals(size - 1, productRepository.count());
        Assertions.assertEquals(amoutOfOrders, orderRepository.count());

    }

}
