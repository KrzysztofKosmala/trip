package pl.kosmala.shop.order.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.service.OrderService;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto)
    {
        return orderService.placeOrder(orderDto);
    }
}
