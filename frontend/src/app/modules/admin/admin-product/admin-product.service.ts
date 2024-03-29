import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from 'src/app/shared/model/page';
import { AdminProduct } from './adminProduct';

@Injectable({
  providedIn: 'root'
})
export class AdminProductService {


  constructor(private http: HttpClient) { }

  getProducts(page: number, size: number) : Observable<Page<AdminProduct>>
  {
      return this.http.get<Page<AdminProduct>>(`/api/v1/admin/trips?page=${page}&size=${size}`);
  }

  deactivateTrip(id: number): Observable<AdminProduct>{
    return this.http.put<AdminProduct>('/api/v1/admin/trips/deactivate/'+id, {})
  }
}
