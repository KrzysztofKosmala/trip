package pl.kosmala.shop.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import pl.kosmala.shop.product.model.Product;
import pl.kosmala.shop.product.model.ProductCurrency;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "trip")
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

    @Builder
    public AdminTrip(String name, String desc, ProductCurrency currency, String category, String slug, String destination, BigDecimal basePrice) {
        super(name, desc, currency, category, slug);
        this.destination = destination;
        this.basePrice = basePrice;
    }
    @Builder(builderMethodName = "builderWithId", buildMethodName = "buildWithId")
    public AdminTrip(Long id, String name, String desc, ProductCurrency currency, String category, String slug, String destination, BigDecimal basePrice) {
        super(id, name, desc, currency, category, slug);
        this.destination = destination;
        this.basePrice = basePrice;
    }


}
