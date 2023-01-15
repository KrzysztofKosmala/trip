package pl.kosmala.shop.product.trip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.*;
import pl.kosmala.shop.product.model.Product;
import pl.kosmala.shop.product.model.ProductCurrency;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@Table(name = "trip")
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



    @Builder
    public Trip(String name, String desc, ProductCurrency currency, String category, String slug, String destination, BigDecimal basePrice) {
        super(name, desc, currency, category, slug);
        this.destination = destination;
        this.basePrice = basePrice;
    }
}
