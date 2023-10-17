package pl.kosmala.shop.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.order.model.dto.InitOrder;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.order.model.dto.OrderListDto;
import pl.kosmala.shop.order.model.dto.OrderSummary;
import pl.kosmala.shop.order.service.OrderService;
import pl.kosmala.shop.order.service.PaymentService;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.order.service.TransportService;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final TransportService transportService;
    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user)
    {
        return orderService.placeTripOrder(orderDto, user);
    }

    @GetMapping("/initData")
    public InitOrder initData()
    {

        return InitOrder.builder()
                .payments(paymentService.getPayments())
                .transports(transportService.getTransports())
                .build();
    }

    @GetMapping()
    public List<OrderListDto> getOrders(@AuthenticationPrincipal User user)
    {
        if(user == null){
            throw new IllegalArgumentException("Brak użytkownika!");
        }
        return orderService.getOrdersForCustomer(user);
    }

    @GetMapping(params = "id")
    public OrderDto getOrderById(@RequestParam Long id, @AuthenticationPrincipal User user)
    {
        if(user == null){
            throw new IllegalArgumentException("Brak użytkownika!");
        }
        return orderService.getOrderById(id, user);
    }
}
