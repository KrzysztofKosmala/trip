package pl.kosmala.shop.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderSummary
{
    private Long id;
    private LocalDateTime placeDate;
    private String status;
    private BigDecimal grossValue;
}
