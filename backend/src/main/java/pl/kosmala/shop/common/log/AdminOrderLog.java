package pl.kosmala.shop.common.log;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_log")
@DiscriminatorValue("order")
@Getter
@NoArgsConstructor
public class AdminOrderLog extends Log
{

    private Long orderId;

    private String note;

    @Builder
    public AdminOrderLog(Long orderId, String note)
    {
        super();
        this.orderId = orderId;
        this.note = note;
    }
}
