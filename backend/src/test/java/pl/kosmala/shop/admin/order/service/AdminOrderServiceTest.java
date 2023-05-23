package pl.kosmala.shop.admin.order.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.admin.order.repository.AdminOrderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminOrderServiceTest
{

    @Mock
    private AdminOrderRepository orderRepository;

    @InjectMocks
    private AdminOrderService adminOrderService;

    @Test
    @DisplayName("Should throw an exception when the order id is not found")
    void patchOrderWhenOrderIdNotFoundThenThrowException()
    {
        Long orderId = 1L;
        Map<String, String> values = new HashMap<>();
        values.put("orderStatus", "PAID");

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> adminOrderService.patchOrder(orderId, values));
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(0)).save(any(AdminOrder.class));
    }

    @Test
    @DisplayName("Should update the order status when valid order id and status are provided")
    void patchOrderWhenValidOrderIdAndStatusProvided()
    {
        Long orderId = 1L;
        AdminOrder existingOrder =
                AdminOrder.builder().id(orderId).orderStatus(OrderStatus.NEW).build();
        Map<String, String> values = new HashMap<>();
        values.put("orderStatus", OrderStatus.PAID.name());

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

        adminOrderService.patchOrder(orderId, values);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
        assertThat(existingOrder.getOrderStatus()).isEqualTo(OrderStatus.PAID);
    }

    @Test
    @DisplayName("Should not update the order status when invalid status is provided")
    void patchOrderWhenInvalidStatusProvided()
    {
        Long orderId = 1L;
        AdminOrder adminOrder = new AdminOrder();
        adminOrder.setId(orderId);
        adminOrder.setOrderStatus(OrderStatus.NEW);

        Map<String, String> values = new HashMap<>();
        values.put("orderStatus", "INVALID_STATUS");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(adminOrder));

        assertThrows(
                IllegalArgumentException.class,
                () -> adminOrderService.patchOrder(orderId, values));

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(adminOrder);
        assertEquals(OrderStatus.NEW, adminOrder.getOrderStatus());
    }
}