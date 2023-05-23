import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { withModule } from '@angular/core/testing';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {

  constructor(private http: HttpClient) 
  {

   }

   resetPassword(resetObject: any): Observable<any>
   {
    console.log(resetObject)
    return this.http.post("/api/v1/password/reset", resetObject);
   }

   changePassword(passwordObject: any): Observable<any> {
    return this.http.post("/api/v1/password/change", passwordObject);
    }
    
}
