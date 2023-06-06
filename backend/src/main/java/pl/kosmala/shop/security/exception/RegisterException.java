package pl.kosmala.shop.security.exception;

import pl.kosmala.shop.common.exception.BusinessException;

public class RegisterException extends BusinessException
{

    public RegisterException(String message)
    {
        super(message);
    }
}


