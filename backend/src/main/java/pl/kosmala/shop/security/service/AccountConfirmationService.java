package pl.kosmala.shop.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.notification.mail.AccountConfirmationEmailService;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.security.payload.ConfirmAccountRequest;
import pl.kosmala.shop.common.user.repository.UserRepository;
import pl.kosmala.shop.security.service.utils.UserHashUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountConfirmationService
{
    @Value("${app.frontAddress}")
    private String serviceAddress;
    private final UserRepository userRepository;
    private final AccountConfirmationEmailService accountConfirmationEmailService;

    @Transactional
    public void sendEmailConfirmationLink(String email)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Taki email nie istnieje"));

        String hash = UserHashUtils.generateHashBasedOnUser(user);
        user.setHash(hash);
        user.setHashDate(LocalDateTime.now());

        EmailMessage message = new EmailMessage();

        message.setTo(email);
        message.setBody(createMessage(createAccountConfirmationLink(hash)));
        message.setSubject("Potwierdź swojego maila.");

        accountConfirmationEmailService.send(message);
    }
    @Transactional
    public void confirmAccount(ConfirmAccountRequest changePassword)
    {

        User user = userRepository.findByHash(changePassword.getHash())
                .orElseThrow(() -> new
                        RuntimeException("Nieprawidłowy link"));
        if(user.getHashDate().plusMinutes(10).isAfter(LocalDateTime.now()))
        {
            user.setEnabled(true);
            user.setHash(null);
            user.setHashDate(null);
        } else {
            throw new RuntimeException("Link stracił ważność");
        }
    }
    private String createMessage(String hashLink) {
        return "Wygenerowaliśmy dla Ciebie link do potwierdzenia emaila" +
                "\n\nKliknij link, żeby aktywować konto: " +
                "\n" + hashLink +
                "\n\nDziękujemy.";
    }
    private String createAccountConfirmationLink(String hash)
    {
        return  serviceAddress + "/confirmAccount/" + hash;
    }
}
