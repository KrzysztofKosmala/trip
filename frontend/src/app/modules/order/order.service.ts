import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InitData } from './model/InitData';
import { OrderDto } from './model/OrderDto';
import { OrderSummary } from './model/OrderSummary';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  placeOrder(order: OrderDto): Observable<OrderSummary>
  {
    console.log(order)
    return this.http.post<OrderSummary>("/api/v1/orders", order);
  }

  getInitData(): Observable<InitData>
  {
    return this.http.get<InitData>("/api/v1/orders/initData");
  }

  getOrder(id: string): Observable<OrderDto>
  {
    let params = new HttpParams().set('id', id);
    
    return this.http.get<OrderDto>("/api/v1/orders", { params })
  }
}
