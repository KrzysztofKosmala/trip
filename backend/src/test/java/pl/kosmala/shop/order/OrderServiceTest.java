package pl.kosmala.shop.order;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.notification.mail.EmailConfig;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.fakeData.UserGenerator;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.PaymentType;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.order.service.OrderService;
import pl.kosmala.shop.security.entity.User;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest
{

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RabbitMQMessageProducer messageProducer;

    @Mock
    private EmailConfig emailConfig;

    @InjectMocks
    private OrderService underTest;

    Faker faker = new Faker();

    ProductGenerator productGenerator = new ProductGenerator();
    UserGenerator userGenerator = new UserGenerator();

    @Test
    void shouldPlaceOrder()
    {

        Trip product = productGenerator.generateTrip();
        OrderDto orderDto = OrderDto.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .city(faker.address().city())
                .zipcode(faker.address().zipCode())
                .email(faker.internet().emailAddress())
                .paymentId(1L)
                .phone(faker.phoneNumber().cellPhone())
                .productslug("product-slug")
                .street(faker.address().streetName())
                .build();


        User user = userGenerator.generateUser();


        Payment payment = new Payment();
        payment.setId(1L);
        payment.setNote("note");
        payment.setName("name");
        payment.setType(PaymentType.BANK_TRANSFER);


        Order order = Order.builder()
                .orderStatus(OrderStatus.NEW)
                .placeDate(LocalDateTime.now())
                .id(1L)
                .grossValue(product.getBasePrice())
                .street(orderDto.getStreet())
                .email(orderDto.getStreet())
                .payment(payment)
                .city(orderDto.getCity())
                .phone(orderDto.getPhone())
                .user(user)
                .build();

        when(productRepository.findBySlug(orderDto.getProductslug())).thenReturn(Optional.of(product));
        when(paymentRepository.findById(orderDto.getPaymentId())).thenReturn(Optional.of(payment));
        when(orderRepository.save(ArgumentMatchers.any())).thenReturn(order);
        when(emailConfig.getInternalExchange()).thenReturn("test" );
        when(emailConfig.getInternalOrderConfirmationRoutingKey()).thenReturn("rautingKey");


        OrderSummary orderSummary = underTest.placeOrder(orderDto, user);


        // then
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(orderRepository).save(orderCaptor.capture());
        Order orderToSave = orderCaptor.getValue();
        assertNotNull(orderToSave);
        assertEquals(product.getName(), orderToSave.getProduct().getName());
        assertEquals(product.getBasePrice(), orderToSave.getProduct().getBasePrice());
        assertEquals(orderDto.getFirstname(), orderToSave.getFirstname());
        assertEquals(orderDto.getLastname(), orderToSave.getLastname());
        assertEquals(orderDto.getStreet(), orderToSave.getStreet());
        assertEquals(orderDto.getCity(), orderToSave.getCity());
        assertEquals(orderDto.getZipcode(), orderToSave.getZipcode());
        assertEquals(orderDto.getPaymentId(), orderToSave.getPayment().getId());
        assertEquals(product.getId(), orderToSave.getProduct().getId());


        assertEquals(orderToSave.getOrderStatus(), orderSummary.getStatus());
        assertEquals(orderToSave.getGrossValue(), orderSummary.getGrossValue());
        assertEquals(payment, orderSummary.getPayment());

        Mockito.verify(messageProducer).publish(Mockito.any(EmailMessage.class), Mockito.anyString(), Mockito.anyString());
    }
}
