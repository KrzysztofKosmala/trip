package pl.kosmala.shop.admin.order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.notification.mail.EmailMessage;
import pl.kosmala.shop.common.notification.mail.OrderChangeStatusEmailService;

@Service
@AllArgsConstructor
public class EmailNotificationForStatusChange
{

    private final OrderChangeStatusEmailService changeStatusEmailService;

    void sendEmailNotification(OrderStatus newStatus, AdminOrder adminOrder)
    {
        EmailMessage emailMessage = AdminOrderEmailMessage.createEmailMessage(newStatus, adminOrder);

        changeStatusEmailService.send(emailMessage);
    }
}
