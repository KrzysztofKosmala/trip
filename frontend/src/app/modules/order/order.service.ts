import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderDto } from './model/OrderDto';
import { OrderSummary } from './model/OrderSummary';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  placeOrder(order: OrderDto): Observable<OrderSummary>
  {
    return this.http.post<OrderSummary>("/api/v1/orders", order);
  }
}
