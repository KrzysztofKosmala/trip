package pl.kosmala.shop.order.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.kosmala.shop.common.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderListDto
{
    private Long id;
    private LocalDateTime placeDate;
    private String orderStatus;
    private BigDecimal grossValue;
    private String productName;
}
