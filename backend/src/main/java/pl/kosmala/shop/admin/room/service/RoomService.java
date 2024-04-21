package pl.kosmala.shop.admin.room.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.room.dtp.RoomMate;
import pl.kosmala.shop.admin.room.exception.RoomBelongingException;
import pl.kosmala.shop.admin.room.model.Room;
import pl.kosmala.shop.admin.room.repository.RoomRepository;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.log.Log;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.service.UserService;
import pl.kosmala.shop.order.repository.OrderRepository;
import pl.kosmala.shop.order.service.OrderService;
import pl.kosmala.shop.trip.service.TripService;

import java.util.ArrayList;
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

        List<String> emails = new ArrayList<String>(friendEmails);

        emails.add(user.getEmail());
        checkIfUsersBelongsToRoomInTrip(emails, slug);

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

    void checkIfUsersBelongsToRoomInTrip(List<String> emails, String slug)
    {
        for (String email : emails)
        {
            if (roomRepository.doesUserBelongToRoomInTrip(email, slug))
            {
                throw new RoomBelongingException(String.format("%1$s already belongs to the room in trip: %2$s", email, slug));
            }
        }
    }

}
