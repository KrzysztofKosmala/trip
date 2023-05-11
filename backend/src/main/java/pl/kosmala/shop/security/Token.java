package pl.kosmala.shop.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token
{
    private String token;
    private boolean adminAccess;
}
