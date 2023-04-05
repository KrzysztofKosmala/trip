package pl.kosmala.shop.common.mail;

public interface EmailSender
{
    void send(String to, String subject, String message);
}
