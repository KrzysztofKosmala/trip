package pl.kosmala.shop.common.notification.mail;

import org.springframework.stereotype.Service;

@Service
public class WebServiceEmailService implements EmailSender
{
    @Override
    public void send(EmailMessage message)
    {
        throw new RuntimeException("not implemented yet");
    }
}
