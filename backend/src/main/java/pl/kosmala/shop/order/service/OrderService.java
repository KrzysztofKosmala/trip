package pl.kosmala.shop.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.mail.*;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.OrderStatus;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.trip.repository.ProductRepository;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    private final RabbitMQMessageProducer messageProducer;
    private final EmailConfig emailConfig;
    private final EmailClientService emailClientService;
    //TODO: dodać klienta do zamówienia
    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto)
    {
        Product product = productRepository.findBySlug(orderDto.getProductslug()).orElseThrow();

        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();

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
                .payment(payment)
                .build();

        product.addOrder(order);

        Order newOrder = orderRepository.save(order);

        EmailMessage message = EmailMessage.builder()
                .to(orderDto.getEmail())
                .body(createEmailMessage(order))
                .subject("Twoje zamówienie zostało przyjęte")
                .build();

        //emailClientService.getInstance().send(message);

        messageProducer.publish
                (
                        message,
                        emailConfig.getInternalExchange(),
                        emailConfig.getInternalOrderConfirmationRoutingKey()
                );

        return OrderSummary.builder()
                .status(newOrder.getOrderStatus())
                .id(newOrder.getId())
                .grossValue(newOrder.getGrossValue())
                .placeDate(newOrder.getPlaceDate())
                .payment(newOrder.getPayment())

                .build();
    }

    private String createEmailMessage(Order order)
    {
        return "Twoje zamówienie o id: " + order.getId() +
                "\nData złożenia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm")) +
                "\nWartość: " + order.getGrossValue() +
                "\n\n" +
                "\nPłatność: " + order.getPayment().getName() + (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nDziękujemy za zakupy.";
    }
}
