package pl.kosmala.shop.common.notification.mail;

import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;
@Service
public class OrderConfirmationEmailService extends AbstractEmailService
{
    public OrderConfirmationEmailService(RabbitMQMessageProducer messageProducer, EmailConfig emailConfig)
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
