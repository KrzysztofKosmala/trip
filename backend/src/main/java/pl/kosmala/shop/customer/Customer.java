package pl.kosmala.shop.customer;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class Customer
{
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
            (
                    name = "first_name",
                    nullable = false,
                    columnDefinition = "TEXT"
            )
    private String firstName;
    @Column
            (
                    name = "last_name",
                    nullable = false,
                    columnDefinition = "TEXT"
            )
    private String lastName;
    @Column
            (
                    name = "email",
                    nullable = false,
                    columnDefinition = "TEXT",
                    unique = true
            )
    private String email;

    @Column
            (
                    name = "password",
                    nullable = false
            )
    private String password;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
