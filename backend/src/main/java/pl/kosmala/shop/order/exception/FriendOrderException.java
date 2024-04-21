package pl.kosmala.shop.order.exception;

import pl.kosmala.shop.common.exception.BusinessException;

public class FriendOrderException extends BusinessException
{
    public FriendOrderException(String message)
    {
        super(message);
    }
}