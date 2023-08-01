package pl.kosmala.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.admin.order.controller.dto.AdminInitDataDto;
import pl.kosmala.shop.admin.order.controller.dto.AdminOrderDto;
import pl.kosmala.shop.admin.order.controller.mapper.AdminOrderMapper;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.admin.order.service.AdminOrderService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController
{

    private final AdminOrderService adminOrderService;

    @GetMapping
    public Page<AdminOrderDto> getOrders(Pageable pageable) {
        Page<AdminOrder> orders = adminOrderService.getOrders(pageable);
        return AdminOrderMapper.mapToPageDtos(orders);
    }

    @GetMapping("/{id}")
    public AdminOrder getOrders(@PathVariable Long id) {
        return adminOrderService.getOrder(id);
    }

    @PatchMapping("/{id}")
    public void patchOrder(@PathVariable Long id, @RequestBody Map<String, String> values)
    {
        adminOrderService.patchOrder(id, values);
    }

    @GetMapping("/initData")
    public AdminInitDataDto getInitData()
    {
        return new AdminInitDataDto(createOrderStatusesMap());
    }
    /*TODO:
     *  retrive it from repo*/
    private Map<String, String> createOrderStatusesMap() {
        Map<String, String> statuses = new HashMap<>();
        for (OrderStatus status : OrderStatus.values()) {
            statuses.put(status.name(), status.getValue());
        }
        return statuses;
    }
}
