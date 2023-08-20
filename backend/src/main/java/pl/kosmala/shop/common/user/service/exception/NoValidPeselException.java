package pl.kosmala.shop.common.user.service.exception;

import pl.kosmala.shop.common.exception.BusinessException;

public class NoValidPeselException extends BusinessException
{

    public NoValidPeselException(String message)
    {
        super(message);
    }
}
