package pl.kosmala.shop.common.user.service.exception;

import pl.kosmala.shop.common.exception.BusinessException;

public class NoValidPhoneNumberException extends BusinessException
{
    public NoValidPhoneNumberException(String message)
    {
        super(message);
    }
}
