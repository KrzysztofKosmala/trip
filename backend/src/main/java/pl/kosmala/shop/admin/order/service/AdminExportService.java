package pl.kosmala.shop.admin.order.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.admin.order.model.AdminOrderStatus;
import pl.kosmala.shop.admin.order.repository.AdminOrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminExportService
{
    private AdminOrderRepository orderRepository;
    public List<AdminOrder> exportOrders(LocalDateTime from, LocalDateTime to, AdminOrderStatus orderStatus)
    {
        return orderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(from, to, orderStatus);
    }
}
