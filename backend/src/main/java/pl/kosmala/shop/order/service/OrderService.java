package pl.kosmala.shop.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.notification.mail.EmailConfig;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;
import pl.kosmala.shop.order.model.Order;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.repository.PaymentRepository;
import pl.kosmala.shop.trip.repository.ProductRepository;

import static pl.kosmala.shop.order.service.mapper.OrderMapper.*;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final RabbitMQMessageProducer messageProducer;
    private final EmailConfig emailConfig;
    //TODO: dodać klienta do zamówienia
    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto)
    {
        Product product = productRepository.findBySlug(orderDto.getProductslug()).orElseThrow();

        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();

        Order order = createNewOrder(orderDto, product, payment);

        product.addOrder(order);

        Order newOrder = orderRepository.save(order);

        EmailMessage message = createNewOrderConfirmationEmail(newOrder);

        publishEmailToTheOrderConfirmationQueue(message);

        return mapToOrderSummary(newOrder);
    }

    private void publishEmailToTheOrderConfirmationQueue(EmailMessage message)
    {
        messageProducer.publish
                (
                        message,
                        emailConfig.getInternalExchange(),
                        emailConfig.getInternalOrderConfirmationRoutingKey()
                );
    }
    private EmailMessage createNewOrderConfirmationEmail(Order order)
    {
        return mapToEmailMassage(order);
    }

}
