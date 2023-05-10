package pl.kosmala.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.admin.order.model.dto.AdminOrderStats;
import pl.kosmala.shop.admin.order.service.AdminOrderStatsService;

@RestController
@RequestMapping("/api/v1/admin/orders/stats")
@RequiredArgsConstructor
public class AdminOrderStatsController
{
    private final AdminOrderStatsService adminOrderStatsService;

    @GetMapping
    public AdminOrderStats getAdminOrdersStats()
    {
        return adminOrderStatsService.getStatistics();
    }
}
