package pl.kosmala.shop.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;

import java.math.BigDecimal;

@Getter
@Setter
public class AdminTripDto
{
    @Length(min = 4)
    private String name;
    @Length(min = 4)
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
