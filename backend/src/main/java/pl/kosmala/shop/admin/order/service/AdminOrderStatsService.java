package pl.kosmala.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.admin.order.model.dto.AdminOrderStats;
import pl.kosmala.shop.admin.order.repository.AdminOrderRepository;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;


@Service
@RequiredArgsConstructor
public class AdminOrderStatsService
{
    private final AdminOrderRepository adminOrderRepository;


    public AdminOrderStats getStatistics()
    {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        List<AdminOrder> orders = adminOrderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(
                from,
                to,
                OrderStatus.COMPLETED
        );

        TreeMap<Integer, AdminOrderStatsValue> result = new TreeMap<>();

        for(int i = from.getDayOfMonth(); i <= to.getDayOfMonth(); i++)
        {
            result.put(i, aggregateValues(i, orders));
        }

        return AdminOrderStats.builder()
                .label(result.keySet().stream().toList())
                .sale(result.values().stream().map(o -> o.sales).toList())
                .order(result.values().stream().map(o -> o.orders).toList())
                .build();

    }
    private AdminOrderStatsValue aggregateValues(int dayNr, List<AdminOrder> orders)
    {

        BigDecimal totalValue = BigDecimal.ZERO;
        Long orderCount = 0L;
        for(AdminOrder order : orders)
        {
            if(dayNr == order.getPlaceDate().getDayOfMonth())
            {
                totalValue = totalValue.add(order.getGrossValue());
                orderCount++;
            }
        }
        return new AdminOrderStatsValue(totalValue, orderCount);

    }

    private record AdminOrderStatsValue(BigDecimal sales, Long orders){}
}
