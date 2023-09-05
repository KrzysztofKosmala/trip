package pl.kosmala.shop.admin.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.admin.room.dtp.RoomMate;
import pl.kosmala.shop.admin.room.dtp.RoomMatesDto;
import pl.kosmala.shop.admin.room.service.RoomService;
import pl.kosmala.shop.common.user.entity.User;


@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController
{
    private final RoomService roomService;


    @GetMapping("/roomMate")
    public RoomMate getRoomMate
            (
                    @AuthenticationPrincipal User user,
                    @RequestParam(name = "friendEmail") String friendEmail,
                    @RequestParam(name = "tripId") Long tripId
            )
    {
        return roomService.getRoomMate(friendEmail, tripId);
    }

    @PostMapping("/roomMate")
    public void addRoomMates
            (
                    @AuthenticationPrincipal User user,
                    @RequestBody RoomMatesDto roomMates
            )
    {
        roomService.addRoomMates(user, roomMates.getFriendEmails(), roomMates.getTripId());
    }
}
