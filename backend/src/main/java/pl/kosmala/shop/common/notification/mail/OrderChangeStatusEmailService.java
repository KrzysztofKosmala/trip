package pl.kosmala.shop.common.notification.mail;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;

@Service
public class OrderChangeStatusEmailService extends AbstractEmailService
{

    public OrderChangeStatusEmailService(RabbitMQMessageProducer messageProducer, EmailConfig emailConfig)
    {
        super(messageProducer, emailConfig);
    }

    public void send(EmailMessage message)
    {
        publishEmailToQueue
                (
                    message,
                    emailConfig.getInternalExchange(),
                    emailConfig.getInternalOrderConfirmationRoutingKey()
                );
    }
}
