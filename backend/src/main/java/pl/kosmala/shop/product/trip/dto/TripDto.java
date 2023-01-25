package pl.kosmala.shop.product.trip.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.kosmala.shop.product.model.ProductCurrency;
import pl.kosmala.shop.product.trip.model.TripDestination;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TripDto
{
    @Length(min = 4)
    private String name;
    private String desc;
    @PositiveOrZero
    private BigDecimal basePrice;
    private ProductCurrency currency;
    @Length(min = 4)
    @NotBlank
    private String slug;
    private String fullDesc;
    private TripDestination destination;
    private Boolean slopNearby;
    private Boolean apartment;
    private Boolean house;
    private Boolean wifi;
    private Boolean food;
    private Boolean spa;


}
