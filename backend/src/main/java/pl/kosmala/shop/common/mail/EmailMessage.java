package pl.kosmala.shop.common.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class EmailMessage implements Serializable
{
    private String to;
    private String subject;
    private String body;
}