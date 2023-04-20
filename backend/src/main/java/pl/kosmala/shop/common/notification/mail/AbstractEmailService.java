package pl.kosmala.shop.common.notification.mail;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;

@Service
@AllArgsConstructor
public abstract class AbstractEmailService
{
    protected final RabbitMQMessageProducer messageProducer;
    protected final EmailConfig emailConfig;

    protected void publishEmailToQueue(EmailMessage message, String exchange, String routingKey)
    {
        messageProducer.publish
                (
                        message,
                        exchange,
                        routingKey
                );
    }
}
