package pl.kosmala.shop.admin.trip.model;

import jakarta.persistence.*;
import lombok.*;
import pl.kosmala.shop.admin.room.model.Room;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
                    name = "start_date"
                    //           nullable = false
            )
    private LocalDate startDate;

    @Column
            (
                    name = "stop_date"
                    //           nullable = false
            )
    private LocalDate endDate;

    @OneToMany(mappedBy = "trip", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new LinkedList<>();

    @Builder
    public AdminTrip
            (
                    String name,
                    String desc,
                    ProductCurrency currency,
                    String slug,
                    String fullDesc,
                    TripDestination destination,
                    BigDecimal basePrice,
                    BigDecimal salePrice,
                    Boolean slopNearby,
                    Boolean apartment,
                    Boolean house,
                    Boolean wifi,
                    Boolean food,
                    Boolean spa,
                    LocalDate startDate,
                    LocalDate endDate,
                    Set<Image> images
            )
    {
        super(name, desc, currency, slug, fullDesc, images, basePrice, salePrice);
        this.destination = destination;
        this.food = food;
        this.slopNearby = slopNearby;
        this.spa = spa;
        this.wifi = wifi;
        this.house = house;
        this.apartment = apartment;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Builder(builderMethodName = "builderWithId", buildMethodName = "buildWithId")
    public AdminTrip
            (
                    Long id,
                    String name,
                    String desc,
                    ProductCurrency currency,
                    String slug,
                    String fullDesc,
                    TripDestination destination,
                    BigDecimal basePrice,
                    BigDecimal salePrice,
                    Boolean slopNearby,
                    Boolean apartment,
                    Boolean house,
                    Boolean wifi,
                    Boolean food,
                    Boolean spa,
                    LocalDate startDate,
                    LocalDate endDate,
                    Set<Image> images
            )
    {
        super(id, name, desc, currency, slug, fullDesc, images, basePrice, salePrice);
        this.destination = destination;
        this.food = food;
        this.slopNearby = slopNearby;
        this.spa = spa;
        this.wifi = wifi;
        this.house = house;
        this.apartment = apartment;
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
