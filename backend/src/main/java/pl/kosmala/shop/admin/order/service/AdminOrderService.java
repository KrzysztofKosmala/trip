package pl.kosmala.shop.admin.order.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.admin.order.repository.AdminOrderRepository;
import pl.kosmala.shop.common.log.AdminOrderLog;
import pl.kosmala.shop.common.log.LogService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository orderRepository;
    private final LogService logService;

    private final EmailNotificationForStatusChange emailNotificationForStatusChange;
    public Page<AdminOrder> getOrders(Pageable pageable) {
        return orderRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        Sort.by("id").descending())
        );
    }

    public AdminOrder getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void patchOrder(Long id, Map<String, String> values)
    {
        AdminOrder adminOrder = orderRepository.findById(id).orElseThrow();
        patchValues(adminOrder, values);
    }

    private void patchValues(AdminOrder adminOrder, Map<String, String> values)
    {
        if(values.get("orderStatus") != null)
        {
            processOrderStatusChange(adminOrder, values);
        }
    }

    private void processOrderStatusChange(AdminOrder adminOrder, Map<String, String> values)
    {
        OrderStatus oldStatus = adminOrder.getOrderStatus();
        adminOrder.setOrderStatus(OrderStatus.valueOf(values.get("orderStatus")));

            OrderStatus newStatus = adminOrder.getOrderStatus();
        if(oldStatus == newStatus)
            return;
        logStatusChange(adminOrder.getId(), oldStatus, newStatus);
        orderRepository.save(adminOrder);
        emailNotificationForStatusChange.sendEmailNotification(newStatus, adminOrder);
    }

    private void logStatusChange(Long orderId, OrderStatus oldStatus, OrderStatus newStatus)
    {
        logService.saveAdminOrderLog(AdminOrderLog.builder()
                .orderId(orderId)
                .note(String.format("Zmiana statusu zamównienia z %s na %s", oldStatus, newStatus))
                .build());
    }
}

