package pl.kosmala.shop.admin.room.exception;

import pl.kosmala.shop.common.exception.BusinessException;

public class RoomBelongingException extends BusinessException
{

    public RoomBelongingException(String message)
    {
        super(message);
    }
}