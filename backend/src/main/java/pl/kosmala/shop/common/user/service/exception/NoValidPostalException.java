package pl.kosmala.shop.common.user.service.exception;

import pl.kosmala.shop.common.exception.BusinessException;

public class NoValidPostalException extends BusinessException
{

    public NoValidPostalException(String message)
    {
        super(message);
    }
}
