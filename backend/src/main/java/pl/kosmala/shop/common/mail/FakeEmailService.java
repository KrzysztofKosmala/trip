package pl.kosmala.shop.common.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FakeEmailService implements EmailSender
{
    @Override
    public void send(String to, String subject, String message)
    {
        log.info("Sending email");
    }
}
