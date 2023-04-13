package pl.kosmala.shop.common.notification.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FakeEmailService implements EmailSender
{
    @Override
    public void send(EmailMessage message)
    {
        log.info("Sending email");
    }
}
