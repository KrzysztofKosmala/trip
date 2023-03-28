package pl.kosmala.shop.order.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderRow
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long orderId;
    private Long productId;
    private BigDecimal price;

}
