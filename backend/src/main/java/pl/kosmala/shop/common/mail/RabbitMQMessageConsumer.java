package pl.kosmala.shop.common.mail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageConsumer
{
    private final EmailClientService emailClientService;

    @RabbitListener(queues = "${rabbitmq.queue.orderConfirmation}")
    public void orderConfirmationConsumer(EmailMessage message)
    {
        log.info("Consumed {} from queue", message);
        emailClientService.getInstance().send(message);
    }
}
