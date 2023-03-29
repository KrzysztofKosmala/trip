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
    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto)
    {
        Product product = productRepository.findBySlug(orderDto.getProductSlug()).orElseThrow();

        // stworzenie zamóweniea z wierszami
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

        Order newOrder = orderRepository.save(order);
        // pobieranie produktu
        // zapisać zamówienie
        return null;
    }
}
