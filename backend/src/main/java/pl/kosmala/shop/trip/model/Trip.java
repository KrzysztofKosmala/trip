package pl.kosmala.shop.trip.model;

import jakarta.persistence.*;
import lombok.*;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;

import java.math.BigDecimal;
import java.util.Set;

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

    @Column
            (
                    name = "max_capacity"
            )
    private Integer maxCapacity;

    @Builder
    public Trip(String name, String desc, ProductCurrency currency, String slug, String fullDesc, TripDestination destination, BigDecimal basePrice, Set<Image> images, BigDecimal salePrice) {
        super(name, desc, currency, slug, fullDesc, images, basePrice, salePrice);
        this.destination = destination;

    }
}
