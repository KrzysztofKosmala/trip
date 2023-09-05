package pl.kosmala.shop.admin.room.dtp;

import lombok.Getter;

@Getter

public class RoomMate
{
    private int numberOfRoomMates;
    private String friendEmail;
    public RoomMate(String friendEmail)
    {
        this.friendEmail = friendEmail;
    }
}
