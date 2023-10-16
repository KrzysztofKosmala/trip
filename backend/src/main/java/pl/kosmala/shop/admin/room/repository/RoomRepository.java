package pl.kosmala.shop.admin.room.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kosmala.shop.admin.room.model.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long>
{

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r JOIN r.users u WHERE u.email = :email AND r.trip.slug = :slug")
    boolean doesUserBelongToRoomInTrip(@Param("email") String email, @Param("slug") String slug);

    @Query("SELECT r FROM Room r JOIN r.users u WHERE u.email = :email AND r.trip.slug= :slug")
    Optional<Room> findRoomByUserEmailAndTripId(@Param("email") String email, @Param("slug") String slug);


}
