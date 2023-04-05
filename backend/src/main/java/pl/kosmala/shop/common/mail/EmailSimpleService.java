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
    public void send(String to, String subject, String msg)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shop <sklep@kosmala.pl>");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
