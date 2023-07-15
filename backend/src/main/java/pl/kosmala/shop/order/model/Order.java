package pl.kosmala.shop.order.model;

import jakarta.persistence.*;
import lombok.*;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order<T extends Product>
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime placeDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal grossValue;
    private String firstname;
    private String lastname;
    private String street;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private T product;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return  Objects.equals(email, order.email) &&
                Objects.equals(city, order.city) &&
                Objects.equals(street, order.street) &&
                Objects.equals(zipcode, order.zipcode) &&
                Objects.equals(grossValue, order.grossValue) &&
                Objects.equals(firstname, order.firstname) &&
                Objects.equals(lastname, order.lastname) &&
                Objects.equals(placeDate, order.placeDate) &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, city, street, zipcode, grossValue, firstname, lastname, placeDate, orderStatus);
    }
}
