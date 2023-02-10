package pl.kosmala.shop.product.trip.model;

import jakarta.persistence.*;
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
                    name = "base_price"
         //           nullable = false
            )
    private BigDecimal basePrice;

    @Column
            (
                    name = "destination",
                    nullable = false,
                    columnDefinition = "TEXT"

            )
    @Enumerated(EnumType.STRING)
    private TripDestination destination;

    @Column
            (
                    name = "slop_nearby"
                    //           nullable = false
            )
    private Boolean slopNearby;

    @Column
            (
                    name = "apartment"
                    //           nullable = false
            )
    private Boolean apartment;

    @Column
            (
                    name = "house"
                    //           nullable = false
            )
    private Boolean house;

    @Column
            (
                    name = "wifi"
                    //           nullable = false
            )
    private Boolean wifi;

    @Column
            (
                    name = "food"
                    //           nullable = false
            )
    private Boolean food;

    @Column
            (
                    name = "spa"
                    //           nullable = false
            )
    private Boolean spa;


    @Builder
    public Trip(String name, String desc, ProductCurrency currency, String slug, String fullDesc, TripDestination destination, BigDecimal basePrice) {
        super(name, desc, currency, slug, fullDesc);
        this.destination = destination;
        this.basePrice = basePrice;
    }
}
