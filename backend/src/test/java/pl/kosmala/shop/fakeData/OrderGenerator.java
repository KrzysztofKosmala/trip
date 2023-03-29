package pl.kosmala.shop.fakeData;

import com.github.javafaker.Faker;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderGenerator
{
    Faker faker = new Faker();

    public Order generateOrder(Product product)
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
                .build();
        product.addOrder(order);
        return order;

    }

    public List<Order> generateOrders(List<? extends Product> products, int howManyOrdersPerProduct)
    {
        List<Order> orders = new ArrayList<>();

        for (Product product : products) {
            for (int i = 0; i < howManyOrdersPerProduct; i++) {
                Order order = generateOrder(product);
                orders.add(order);
            }
        }

        return orders;
    }
}
