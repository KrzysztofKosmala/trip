package pl.kosmala.shop.common.notification.mail;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EmailMessage implements Serializable
{
    private String to;
    private String subject;
    private String body;
}