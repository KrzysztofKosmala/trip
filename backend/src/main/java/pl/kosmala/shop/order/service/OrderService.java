package pl.kosmala.shop.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.room.service.RoomService;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.common.notification.mail.OrderConfirmationEmailService;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderListDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.trip.repository.ProductRepository;

import java.util.List;

import static pl.kosmala.shop.order.service.mapper.OrderMapper.*;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AdminTripRepository adminTripRepository;
    private final PaymentRepository paymentRepository;
    private final RoomService roomService;
    private final OrderConfirmationEmailService orderConfirmationEmailService;
    //TODO: sprawdzic czy uzutkownik juz zarezerwowal te wycieczke, dodac znajomych
    @Transactional
    public OrderSummary placeTripOrder(OrderDto orderDto, User user)
    {
        AdminTrip product = adminTripRepository.findAdminTripBySlug(orderDto.getProductslug()).orElseThrow();

        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();

        Order<Product> order = createNewOrder(orderDto, product, payment, user);

        product.addOrder(order);
        if(!orderDto.getFriendEmails().isEmpty())
            roomService.addRoomMates(user, orderDto.getFriendEmails(), orderDto.getProductslug());

        Order<Product> newOrder = orderRepository.save(order);

        EmailMessage message = createNewOrderConfirmationEmail(newOrder);

        orderConfirmationEmailService.send(message);

        return mapToOrderSummary(newOrder);
    }

    private EmailMessage createNewOrderConfirmationEmail(Order order)
    {
        return mapToEmailMassage(order);
    }

    public List<OrderListDto> getOrdersForCustomer(User user)
    {
        return mapToOrderListDto(orderRepository.findByUserId(user.getId()));
    }


    private List<OrderListDto> mapToOrderListDto(List<Order> byUserId)
    {
        return byUserId.stream()
                .map
                        (
                                order -> OrderListDto.builder()
                                        .id(order.getId())
                                        .placeDate(order.getPlaceDate())
                                        .grossValue(order.getGrossValue())
                                        .orderStatus(order.getOrderStatus().getValue())
                                        .productName(order.getProduct().getName())
                                        .build()
                        )
                .toList();
    }

    public OrderDto getOrderById(Long id, User user)
    {

        Order order = orderRepository.findById(id).orElseThrow();

        //throw sprawdzenie czy order nalezy do usera
        order.getUser().getId().equals(user.getId());

        return mapToOrderDto(order);

    }
}
