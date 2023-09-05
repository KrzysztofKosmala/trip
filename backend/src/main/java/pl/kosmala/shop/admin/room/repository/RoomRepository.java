package pl.kosmala.shop.admin.room.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kosmala.shop.admin.room.model.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long>
{

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r JOIN r.users u WHERE u.email = :email AND r.trip.id = :tripId")
    boolean doesUserBelongToRoomInTrip(@Param("email") String email, @Param("tripId") Long tripId);

    @Query("SELECT r FROM Room r JOIN r.users u WHERE u.email = :email AND r.trip.id = :tripId")
    Optional<Room> findRoomByUserEmailAndTripId(@Param("email") String email, @Param("tripId") Long tripId);


}
