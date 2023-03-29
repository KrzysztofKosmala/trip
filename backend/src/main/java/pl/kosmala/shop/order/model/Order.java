package pl.kosmala.shop.order.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import pl.kosmala.shop.common.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "product_id")
    private T product;


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
