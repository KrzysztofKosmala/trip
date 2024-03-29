package pl.kosmala.shop.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.common.notification.mail.PasswordResetEmailService;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.security.payload.ChangePassword;
import pl.kosmala.shop.security.payload.ResetPasswordRequest;
import pl.kosmala.shop.common.user.repository.UserRepository;
import pl.kosmala.shop.security.service.utils.UserHashUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResetPasswordService
{
    @Value("${app.frontAddress}")
    private String serviceAddress;
    private final UserRepository userRepository;
    private final PasswordResetEmailService passwordResetEmailService;


    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void sendLostPasswordLink(ResetPasswordRequest email)
    {
        User user = userRepository.findByEmail(email.getEmail())
                .orElseThrow(() -> new RuntimeException("Taki email nie istnieje"));

        String hash = UserHashUtils.generateHashBasedOnUser(user);
        user.setHash(hash);
        user.setHashDate(LocalDateTime.now());

        EmailMessage message = new EmailMessage();

        message.setTo(email.getEmail());
        message.setBody(createMessage(createResetPasswordLink(hash)));
        message.setSubject("Link do resetowania hasła.");

        passwordResetEmailService.send(message);
    }
    @Transactional
    public void changePassword(ChangePassword changePassword)
    {
        if(!Objects.equals(changePassword.getPassword(), changePassword.getRepeatPassword()))
        {
            throw new RuntimeException("Hasła nie są takie same");
        }
        User user = userRepository.findByHash(changePassword.getHash())
                .orElseThrow(() -> new
                        RuntimeException("Nieprawidłowy link"));
        if(user.getHashDate().plusMinutes(10).isAfter(LocalDateTime.now()))
        {
            user.setPassword(passwordEncoder.encode(changePassword.getPassword()));
            user.setHash(null);
            user.setHashDate(null);
        } else {
            throw new RuntimeException("Link stracił ważność");
        }
    }


    String createResetPasswordLink(String hash)
    {
        return  serviceAddress + "/resetPassword/" + hash;
    }

    private String createMessage(String hashLink) {
        return "Wygenerowaliśmy dla Ciebie link do zmiany hasła" +
                "\n\nKliknij link, żeby zresetować hasło: " +
                "\n" + hashLink +
                "\n\nDziękujemy.";
    }

}
