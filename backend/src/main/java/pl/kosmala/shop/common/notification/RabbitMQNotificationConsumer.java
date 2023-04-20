package pl.kosmala.shop.common.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.kosmala.shop.common.notification.mail.EmailClientService;
import pl.kosmala.shop.common.notification.mail.EmailMessage;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQNotificationConsumer
{
    private final EmailClientService emailClientService;

    @RabbitListener(queues = "${rabbitmq.queue.orderConfirmation}")
    public void orderConfirmationConsumer(EmailMessage message)
    {
        log.info("Consumed {} from queue", message);
        emailClientService.getInstance().send(message);
    }
    @RabbitListener(queues = "${rabbitmq.queue.orderChangeStatus}")
    public void orderChangeStatusConsumer(EmailMessage message)
    {
        log.info("Consumed {} from queue", message);
        emailClientService.getInstance().send(message);
    }
}
