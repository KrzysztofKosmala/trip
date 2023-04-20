package pl.kosmala.shop.admin.order.model;

import jakarta.persistence.*;
import lombok.*;
import pl.kosmala.shop.common.log.AdminOrderLog;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.order.model.OrderStatus;
import pl.kosmala.shop.order.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "`order`")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AdminOrder<T extends Product>
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime placeDate;
    @Enumerated(EnumType.STRING)
    private AdminOrderStatus orderStatus ;
    private BigDecimal grossValue;
    private String firstname;
    private String lastname;
    private String street;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "product_id")
    private T product;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private AdminPayment payment;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<AdminOrderLog> orderLogs;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminOrder<?> that = (AdminOrder<?>) o;
        return Objects.equals(placeDate, that.placeDate) && orderStatus == that.orderStatus && Objects.equals(grossValue, that.grossValue) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(street, that.street) && Objects.equals(zipcode, that.zipcode) && Objects.equals(city, that.city) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(product, that.product) && Objects.equals(payment, that.payment);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(placeDate, orderStatus, grossValue, firstname, lastname, street, zipcode, city, email, phone, product, payment);
    }
}
