package pl.kosmala.shop.order.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.notification.mail.EmailConfig;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.common.notification.mail.OrderConfirmationEmailService;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;
import pl.kosmala.shop.fakeData.OrderGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.fakeData.UserGenerator;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.PaymentType;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest
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
    private OrderConfirmationEmailService orderConfirmationEmailService;

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
/*        when(emailConfig.getInternalExchange()).thenReturn("test" );
        when(emailConfig.getInternalOrderConfirmationRoutingKey()).thenReturn("rautingKey");*/
        Mockito.doNothing().when(orderConfirmationEmailService).send(Mockito.any(EmailMessage.class));


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
        assertEquals(user.getId(), orderToSave.getUser().getId());


        assertEquals(orderToSave.getOrderStatus(), orderSummary.getStatus());
        assertEquals(orderToSave.getGrossValue(), orderSummary.getGrossValue());
        assertEquals(payment, orderSummary.getPayment());

        //Mockito.verify(messageProducer).publish(Mockito.any(EmailMessage.class), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void shouldMap()
    {
        OrderGenerator orderGenerator = new OrderGenerator();
        List<Trip> trips = productGenerator.trips(4);

    }
}