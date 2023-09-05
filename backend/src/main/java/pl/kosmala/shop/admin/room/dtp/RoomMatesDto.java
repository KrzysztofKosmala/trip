package pl.kosmala.shop.admin.room.dtp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomMatesDto
{
    private List<String> friendEmails;
    private Long tripId;
}
