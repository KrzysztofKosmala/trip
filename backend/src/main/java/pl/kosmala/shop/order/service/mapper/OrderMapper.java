package pl.kosmala.shop.order.service.mapper;

import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.common.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderMapper
{
    public static Order<Product> createNewOrder(OrderDto orderDto, Product product, Payment payment, User user)
    {

        BigDecimal grossValue;

        if(product.getSalePrice() != null)
            grossValue = product.getSalePrice();
        else
            grossValue = product.getBasePrice();

        return Order.builder()
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
                .grossValue(grossValue)
                .payment(payment)
                .user(user)
                .build();
    }

    public static OrderSummary mapToOrderSummary(Order newOrder)
    {
        return OrderSummary.builder()
                .status(newOrder.getOrderStatus())
                .id(newOrder.getId())
                .grossValue(newOrder.getGrossValue())
                .placeDate(newOrder.getPlaceDate())
                .payment(newOrder.getPayment())
                .build();
    }

    public static EmailMessage mapToEmailMassage(Order order)
    {
        EmailMessage message =  new EmailMessage();
        message.setTo(order.getEmail());
        message.setBody(mapToString(order));
        message.setSubject("Twoje zamówienie zostało przyjęte");

        return message;
    }
    private static String mapToString(Order order)
    {
        return "Twoje zamówienie o id: " + order.getId() +
                "\nData złożenia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm")) +
                "\nWartość: " + order.getGrossValue() +
                "\n\n" +
                "\nPłatność: " + order.getPayment().getName() + (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nDziękujemy za zakupy.";
    }
}
