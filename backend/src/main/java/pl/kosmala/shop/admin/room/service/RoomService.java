package pl.kosmala.shop.admin.room.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.room.dtp.RoomMate;
import pl.kosmala.shop.admin.room.model.Room;
import pl.kosmala.shop.admin.room.repository.RoomRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.service.UserService;
import pl.kosmala.shop.order.service.OrderService;
import pl.kosmala.shop.trip.service.TripService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService
{
    private final UserService userService;
    private final OrderService orderService;
    private final RoomRepository roomRepository;
    private final AdminTripService tripService;
    public RoomMate getRoomMate(String friendEmail, Long tripId)
    {
        if (!userService.checkIfUserExists(friendEmail))
            throw new NoSuchElementException("Wystąpił problem.");


        if (!orderService.hasUserPaidForProduct(friendEmail, tripId))
            throw new NoSuchElementException("Wystąpił problem.");

        return new RoomMate(friendEmail);
    }

    public void addRoomMates(User user, List<String> friendEmails, Long tripId)
    {
        boolean hasFriendRoom = false;
        Room existingRoom = null;
        for (String friendEmail : friendEmails) {
            if (roomRepository.doesUserBelongToRoomInTrip(friendEmail, tripId)) {
                hasFriendRoom = true;
                existingRoom = roomRepository.findRoomByUserEmailAndTripId(friendEmail, tripId)
                        .orElseThrow(() -> new NoSuchElementException("Pokój nie istnieje"));
                break;
            }
        }

        if (hasFriendRoom)
        {
            if (existingRoom.getUsers().contains(user)) {
                throw new IllegalArgumentException("użytkownik już jest w tym pokoju.");
            }
            for (String friendEmail : friendEmails) {
                User friend = userService.getUserByEmail(friendEmail);
                if (!existingRoom.getUsers().contains(friend)) {
                    throw new IllegalArgumentException("Nie wszyscy użytkownicy należą do tego samego pokoju.");
                }
            }
            existingRoom.getUsers().add(user);
            roomRepository.save(existingRoom);
        } else {
            Room newRoom = new Room();
            newRoom.setTrip(tripService.getTripById(tripId));
            for (String friendEmail : friendEmails) {
                User friend = userService.getUserByEmail(friendEmail);
                newRoom.getUsers().add(friend);
            }
            newRoom.getUsers().add(user);
            roomRepository.save(newRoom);
        }
    }
}
