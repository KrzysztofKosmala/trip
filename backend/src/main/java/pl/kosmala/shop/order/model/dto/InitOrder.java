package pl.kosmala.shop.order.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.kosmala.shop.order.model.Payment;

import java.util.List;

@Getter
@Builder
public class InitOrder
{
    private List<Payment> payments;
}
