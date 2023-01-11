import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from 'src/app/shared/model/page';
import { Product } from './model/product';
import { Trip } from './model/trip';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getTrips(page: number, size: number) : Observable<Page<Trip>>
  {
      return this.http.get<Page<Trip>>(`api/v1/trips?page=${page}&size=${size}`);
  }
}
