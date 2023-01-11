package pl.kosmala.shop.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import pl.kosmala.shop.product.model.Product;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class AdminTrip extends Product
{
    @Column
            (
                    name = "destination",
                    //        nullable = false,
                    columnDefinition = "TEXT"
            )
    private String destination;
    @Column
            (
                    name = "base_price"
                    //           nullable = false
            )
    private BigDecimal basePrice;
}
