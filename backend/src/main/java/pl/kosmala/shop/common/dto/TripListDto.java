package pl.kosmala.shop.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
public class TripListDto
{
    private Long id;
    private String name;
    private String desc;
    @PositiveOrZero
    private BigDecimal basePrice;
    @PositiveOrZero
    private BigDecimal salePrice;
    private ProductCurrency currency;
    @Length(min = 4)
    @NotBlank
    private Set<Image> images;
    private String slug;
    private TripDestination destination;
    private Boolean slopNearby;
    private Boolean apartment;
    private Boolean house;
    private Boolean wifi;
    private Boolean food;
    private Boolean spa;

}
