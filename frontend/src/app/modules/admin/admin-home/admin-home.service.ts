import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HomePageSettings } from './model/homePageSettings';

@Injectable({
  providedIn: 'root'
})
export class AdminHomeService {


  saveStatus(value: HomePageSettings): Observable<any> {
    return this.http.patch<void>("/api/v1/admin/homePage" , value);
  }

  constructor(private http: HttpClient) { }

  getInitData(): Observable<any> {
    return this.http.get<any>("/api/v1/admin/homePage/initData")
  }

  getSettings(): Observable<any> {
    return this.http.get<any>("/api/v1/admin/homePage/getSettings")
  }
}
