package pl.kosmala.shop.admin.room.dtp;

import lombok.Getter;

@Getter

public class RoomMate
{
    private int numberOfRoomMates;
    private String email;
    public RoomMate(String email)
    {
        this.email = email;
    }
}
