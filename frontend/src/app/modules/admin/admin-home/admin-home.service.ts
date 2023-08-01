import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminHomeService {

  constructor(private http: HttpClient) { }

  getInitData(): Observable<any> {
    return this.http.get<any>("/api/v1/admin/homePage/initData")
  }

  getSettings(): Observable<any> {
    return this.http.get<any>("/api/v1/admin/homePage/getSettings")
  }
}
