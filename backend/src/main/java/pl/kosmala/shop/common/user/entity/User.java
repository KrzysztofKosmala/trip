package pl.kosmala.shop.common.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kosmala.shop.admin.room.model.Room;
import pl.kosmala.shop.common.user.entity.types.Role;

import java.time.LocalDateTime;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "_user")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;

    private String phone;

    private String pesel;

    @Column
            (
                    name = "gender",
                    nullable = true,
                    columnDefinition = "TEXT"

            )
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String street;

    private String postal;
    private String city;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isEnabled;

    @ManyToMany(mappedBy = "users")
    private List<Room> rooms = new LinkedList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }



    private String hash;
    private LocalDateTime HashDate;
    @Override
    public String getUsername()
    {
        return Long.toString(id);
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return isEnabled;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isEnabled != user.isEnabled) return false;
        if (!Objects.equals(firstname, user.firstname)) return false;
        if (!Objects.equals(lastname, user.lastname)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(phone, user.phone)) return false;
        if (!Objects.equals(pesel, user.pesel)) return false;
        if (gender != user.gender) return false;
        if (!Objects.equals(street, user.street)) return false;
        if (!Objects.equals(postal, user.postal)) return false;
        if (!Objects.equals(password, user.password)) return false;
        return Objects.equals(hash, user.hash);
    }

    @Override
    public int hashCode()
    {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (postal != null ? postal.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        return result;
    }
}
