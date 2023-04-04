package pl.kosmala.shop.order.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.order.model.dto.InitOrder;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.service.OrderService;
import pl.kosmala.shop.order.service.PaymentService;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    private final PaymentService paymentService;
    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto)
    {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/initData")
    public InitOrder initData()
    {
        return InitOrder.builder()
                .payments(paymentService.getPayments())
                .build();
    }
}
