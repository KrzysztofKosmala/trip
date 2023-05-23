package pl.kosmala.shop.security.payload;

import lombok.Getter;

@Getter
public class ChangePassword
{
    private String password;
    private String repeatPassword;
    private String hash;
}

