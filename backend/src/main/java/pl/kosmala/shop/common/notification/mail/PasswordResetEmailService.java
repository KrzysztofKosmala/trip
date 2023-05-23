package pl.kosmala.shop.common.notification.mail;

import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.rabbitMq.RabbitMQMessageProducer;
@Service
public class PasswordResetEmailService extends AbstractEmailService
{
    public PasswordResetEmailService(RabbitMQMessageProducer messageProducer, EmailConfig emailConfig)
    {
        super(messageProducer, emailConfig);
    }

    public void send(EmailMessage message)
    {
        publishEmailToQueue
                (
                        message,
                        emailConfig.getInternalExchange(),
                        emailConfig.getInternalResetPasswordRoutingKey()
                );
    }
}
