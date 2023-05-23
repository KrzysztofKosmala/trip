package pl.kosmala.shop.admin.order.service;

import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.common.model.OrderStatus;
import pl.kosmala.shop.common.notification.mail.EmailMessage;

public class AdminOrderEmailMessage
{

    public static EmailMessage createEmailMessage(OrderStatus newStatus, AdminOrder adminOrder)
    {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(adminOrder.getEmail());
        emailMessage.setTo(createChangeStatusSubject(adminOrder.getId(), newStatus));
        if(newStatus == OrderStatus.COMPLETED)
        {
            emailMessage.setBody(AdminOrderEmailMessage.createCompletedEmailMessage(adminOrder.getId(), newStatus));
        }else if(newStatus == OrderStatus.REFUND)
        {
            emailMessage.setBody(AdminOrderEmailMessage.createRefundEmailMessage(adminOrder.getId(), newStatus));
        }
        return emailMessage;
    }

    private static String createCompletedEmailMessage(Long id, OrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zrealizowane." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n\n Dziekujemuy za zakupy i zapraszamy ponownie" +
                "\n Sklep Shop";
    }

    private static String createRefundEmailMessage(Long id, OrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zwrócone." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n\n Pozdrawiamy" +
                "\n Sklep Shop";
    }

    private static String createChangeStatusSubject(Long id, OrderStatus newStatus)
    {
        return "Zamówienie: " + id + "zmianiło status na: " + newStatus.getValue();
    }

}
