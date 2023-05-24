import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfirmAccountService {

  constructor(private http: HttpClient) 
  {

   }

   confirmAccount(confirmObject: any): Observable<any>
   {

    return this.http.post("/api/v1/auth/confirmAccount", confirmObject);
   }
}
