package pl.kosmala.shop.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdminTripDto
{
    private String name;
    private String desc;
    private BigDecimal basePrice;
    private String currency;
    private String category;
    private String destination;
}
