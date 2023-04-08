package pl.kosmala.shop.common.mail;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public interface EmailSender
{
    void send(EmailMessage message);
}
