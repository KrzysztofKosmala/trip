package pl.kosmala.shop.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class Trip extends Product
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
