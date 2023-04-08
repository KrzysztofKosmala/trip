package pl.kosmala.shop.common.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSimpleService implements EmailSender
{
    private final JavaMailSender mailSender;
    @Override
    public void send(EmailMessage message)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shop <sklep@kosmala.pl>");
        simpleMailMessage.setTo(message.getTo());
        simpleMailMessage.setSubject(message.getSubject());
        simpleMailMessage.setText(message.getBody());
        mailSender.send(simpleMailMessage);
    }
}
