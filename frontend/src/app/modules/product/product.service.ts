import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from 'src/app/shared/model/page';
import { Filter } from './filter';
import { Product } from '../common/model/product';
import { Trip } from '../common/model/trip';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getTrips(page: number, size: number) : Observable<Page<Trip>>
  {
      return this.http.get<Page<Trip>>(`api/v1/trips?page=${page}&size=${size}`);
  }


  getTripsByFilter(page: number, size: number, filter: Filter) : Observable<Page<Trip>>
  {
    let params = new HttpParams()
    .set('page', page.toString())
    .set('size', size.toString());
  if (filter.destination != null ) {
    params = params.set('destination', filter.destination);
  }
  if (filter.slopNearby != null && filter.spa != false) {
    params = params.set('slopNearby', filter.slopNearby.toString());
  }
  if (filter.apartment != null && filter.spa != false) {
    params = params.set('apartment', filter.apartment.toString());
  }
  if (filter.house != null && filter.spa != false) {
    params = params.set('house', filter.house.toString());
  }
  if (filter.wifi != null && filter.spa != false) {
    params = params.set('wifi', filter.wifi.toString());
  }
  if (filter.food != null && filter.spa != false) {
    params = params.set('food', filter.food.toString());
  }
  if (filter.spa != null && filter.spa != false) {
    params = params.set('spa', filter.spa.toString());
  }
  return this.http.get<Page<Trip>>(`api/v1/trips/byFilter`, { params });
  }
}
