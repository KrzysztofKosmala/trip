package pl.kosmala.shop.admin.room.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.common.user.entity.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private AdminTrip trip;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable
            (
                    name = "rooms_users",
                    joinColumns = @JoinColumn(name = "room_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id")
            )
    private Set<User> users = new HashSet<>();
}