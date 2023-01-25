package pl.kosmala.shop.admin.model;

import jakarta.persistence.*;
import lombok.*;
import pl.kosmala.shop.product.model.Product;
import pl.kosmala.shop.product.model.ProductCurrency;
import pl.kosmala.shop.product.trip.model.TripDestination;

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
                    name = "slope_nearby"
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
    public AdminTrip(String name, String desc, ProductCurrency currency, String slug, String fullDesc, TripDestination destination, BigDecimal basePrice, Boolean slopNearby, Boolean apartment, Boolean house, Boolean wifi, Boolean food, Boolean spa) {
        super(name, desc, currency, slug, fullDesc);
        this.destination = destination;
        this.basePrice = basePrice;
        this.food = food;
        this.slopNearby = slopNearby;
        this.spa = spa;
        this.wifi = wifi;
        this.house = house;
        this.apartment = apartment;
    }
    @Builder(builderMethodName = "builderWithId", buildMethodName = "buildWithId")
    public AdminTrip(Long id, String name, String desc, ProductCurrency currency, String slug, String fullDesc, TripDestination destination, BigDecimal basePrice, Boolean slopNearby, Boolean apartment, Boolean house, Boolean wifi, Boolean food, Boolean spa) {
        super(id, name, desc, currency, slug, fullDesc);
        this.destination = destination;
        this.basePrice = basePrice;
        this.food = food;
        this.slopNearby = slopNearby;
        this.spa = spa;
        this.wifi = wifi;
        this.house = house;
        this.apartment = apartment;
    }


}
