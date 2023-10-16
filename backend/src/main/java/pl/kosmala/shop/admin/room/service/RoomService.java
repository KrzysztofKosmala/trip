package pl.kosmala.shop.admin.room.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.room.dtp.RoomMate;
import pl.kosmala.shop.admin.room.model.Room;
import pl.kosmala.shop.admin.room.repository.RoomRepository;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.service.UserService;
import pl.kosmala.shop.order.repository.OrderRepository;
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
    private final RoomRepository roomRepository;
    private final AdminTripService tripService;
    private final OrderRepository orderRepository;

    public RoomMate getRoomMate(String email, String slug)
    {
        if (!userService.checkIfUserExists(email))
            throw new NoSuchElementException("Wystąpił problem.");


        if (!orderRepository.hasUserPaidForProduct(email, slug))
            throw new NoSuchElementException("Wystąpił problem.");

        return new RoomMate(email);
    }

    public void addRoomMates(User user, List<String> friendEmails, String slug)
    {
        boolean hasFriendRoom = false;
        Room existingRoom = null;
        for (String friendEmail : friendEmails) {
            if (roomRepository.doesUserBelongToRoomInTrip(friendEmail, slug)) {
                hasFriendRoom = true;
                existingRoom = roomRepository.findRoomByUserEmailAndTripId(friendEmail, slug)
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
            AdminTrip tripBySlug = tripService.getAdminTripBySlug(slug);

            newRoom.setTrip(tripBySlug);
            for (String friendEmail : friendEmails) {
                User friend = userService.getUserByEmail(friendEmail);
                newRoom.getUsers().add(friend);
            }
            newRoom.getUsers().add(user);
            roomRepository.save(newRoom);
        }
    }
}
