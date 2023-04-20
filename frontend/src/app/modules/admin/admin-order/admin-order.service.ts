import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from 'src/app/shared/model/page';
import { AdminOrder } from './model/adminOrder';

@Injectable({
  providedIn: 'root'
})
export class AdminOrderService {
  getInitData(): Observable<any> {
    return this.http.get<any>("/api/v1/admin/orders/initData")
  }
  
  constructor(private http: HttpClient) { }
  
  getOrders(pageIndex: number, pageSize: number): Observable<Page<AdminOrder>> {
    return this.http.get<Page<AdminOrder>>(`/api/v1/admin/orders?page=${pageIndex}&size=${pageSize}`);
  }
  
  getOrder(id: number): Observable<AdminOrder> {
    return this.http.get<AdminOrder>("/api/v1/admin/orders/" + id);
  }

  saveStatus(id: number, value: any): Observable<void> {
    console.log(id)
    return this.http.patch<void>("/api/v1/admin/orders/" + id, value);
  }
}