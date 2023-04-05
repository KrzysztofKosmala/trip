package pl.kosmala.shop.common.mail;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

//@Configuration
public class EmailConfig
{
    @Bean
    @ConditionalOnProperty(name = "app.email.sender", havingValue = "emailSimpleService", matchIfMissing = true)
    public EmailSender emailSimpleService(JavaMailSender mailSender)
    {
        return new EmailSimpleService(mailSender);
    }

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", havingValue = "fakeEmailService")
    public EmailSender fakeEmailService()
    {
        return new FakeEmailService();
    }
}
