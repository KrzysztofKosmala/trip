package pl.kosmala.shop.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus
{
     NEW("Nowe") , PAID("Opłacone") , PROCESSING("Przetwarzane") , COMPLETED("Zrealizowane") , CANCELED("Anulowane") , REFUND("Zwrócone");

     private String value;


}
