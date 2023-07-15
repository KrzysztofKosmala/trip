package pl.kosmala.shop.fakeData;

import com.github.javafaker.Faker;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.common.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator
{
    Faker faker = new Faker();

    public Order generateOrder(Product product, User user)
    {
        Order<Product> order = Order.builder()
                .orderStatus(OrderStatus.NEW)
                .product(product)
                .email(faker.internet().emailAddress())
                .city(faker.address().city())
                .placeDate(LocalDateTime.now())
                .street(faker.address().streetAddress())
                .zipcode(faker.address().zipCode())
                .grossValue(product.getBasePrice())
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .user(user)
                .build();
        product.addOrder(order);
        return order;

    }

    public List<Order> generateOrders(List<? extends Product> products, int howManyOrdersPerProduct, List<User> users)
    {
        List<Order> orders = new ArrayList<>();
        Random random = new Random();
        for (Product product : products) {
            int randomUserIndex = random.nextInt(users.size());
            for (int i = 0; i < howManyOrdersPerProduct; i++) {
                Order order = generateOrder(product,users.get(randomUserIndex));
                orders.add(order);
            }
        }

        return orders;
    }
}
