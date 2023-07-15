import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderListDto } from './model/orderListDto';
import { UserDetails } from './model/userDetails';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) 
  {

   }

   getDetails(): Observable<UserDetails>
   {
    return this.http.get<UserDetails>("/api/v1/users");
   }

   getOrders(): Observable<Array<OrderListDto>>
   {
    return this.http.get<Array<OrderListDto>>("/api/v1/orders");
   }


   updateDetails(userDetails: UserDetails): Observable<any>
   {
    return this.http.post("/api/v1/users/update", userDetails)
   }
}
