package pl.kosmala.shop.product.trip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.kosmala.shop.product.model.ProductCurrency;

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
    private String category;
    private String destination;
    @Length(min = 4)
    @NotBlank
    private String slug;
}
