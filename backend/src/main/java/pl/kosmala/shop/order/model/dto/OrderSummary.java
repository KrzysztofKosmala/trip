package pl.kosmala.shop.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.kosmala.shop.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class OrderSummary
{
    private Long id;
    private LocalDateTime placeDate;
    private OrderStatus status;
    private BigDecimal grossValue;
}
