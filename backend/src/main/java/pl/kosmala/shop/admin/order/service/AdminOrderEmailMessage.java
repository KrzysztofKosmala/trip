package pl.kosmala.shop.admin.order.service;

import pl.kosmala.shop.admin.order.model.AdminOrder;
import pl.kosmala.shop.admin.order.model.AdminOrderStatus;
import pl.kosmala.shop.common.notification.mail.EmailMessage;

public class AdminOrderEmailMessage
{

    public static EmailMessage createEmailMessage( AdminOrderStatus newStatus, AdminOrder adminOrder)
    {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(adminOrder.getEmail());
        emailMessage.setTo(createChangeStatusSubject(adminOrder.getId(), newStatus));
        if(newStatus == AdminOrderStatus.COMPLETED)
        {
            emailMessage.setBody(AdminOrderEmailMessage.createCompletedEmailMessage(adminOrder.getId(), newStatus));
        }else if(newStatus == AdminOrderStatus.REFUND)
        {
            emailMessage.setBody(AdminOrderEmailMessage.createRefundEmailMessage(adminOrder.getId(), newStatus));
        }
        return emailMessage;
    }

    private static String createCompletedEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zrealizowane." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n\n Dziekujemuy za zakupy i zapraszamy ponownie" +
                "\n Sklep Shop";
    }

    private static String createRefundEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zwrócone." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n\n Pozdrawiamy" +
                "\n Sklep Shop";
    }

    private static String createChangeStatusSubject(Long id, AdminOrderStatus newStatus)
    {
        return "Zamówienie: " + id + "zmianiło status na: " + newStatus.getValue();
    }

}
