package pl.kosmala.shop.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.OrderStatus;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.trip.repository.ProductRepository;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    //TODO: dodać klienta do zamówienia
    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto)
    {
        Product product = productRepository.findBySlug(orderDto.getProductslug()).orElseThrow();

        Order order = Order.builder()
                .firstname(orderDto.getFirstname())
                .lastname(orderDto.getLastname())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .zipcode(orderDto.getZipcode())
                .street(orderDto.getStreet())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .product(product)
                .grossValue(product.getBasePrice())
                .build();

        product.addOrder(order);

        Order newOrder = orderRepository.save(order);

        return OrderSummary.builder()
                .status(newOrder.getOrderStatus())
                .id(newOrder.getId())
                .grossValue(newOrder.getGrossValue())
                .placeDate(newOrder.getPlaceDate())
                .build();
    }
}
