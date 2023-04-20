package pl.kosmala.shop.admin.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminOrderStatus {
     NEW("Nowe") , PAID("Opłacone") , PROCESSING("Przetwarzane") , COMPLETED("Zrealizowane") , CANCELED("Anulowane") , REFUND("Zwrócone");

     private String value;


}
