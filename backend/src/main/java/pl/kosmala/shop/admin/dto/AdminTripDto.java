package pl.kosmala.shop.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.kosmala.shop.product.model.ProductCurrency;

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
    private String category;
    private String destination;
    @Length(min = 4)
    @NotBlank
    private String slug;
}
