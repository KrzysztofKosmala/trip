import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { AdminProductUpdate } from '../model/adminProductUpdate';

@Injectable({
  providedIn: 'root'
})
export class AdminProductAddService {

  constructor(private http: HttpClient) { }

  saveNewProduct(product: AdminProductUpdate): Observable<AdminProductUpdate>{
    console.log(product)
    return this.http.post<AdminProductUpdate>("/api/v1/admin/trips",product)
  }

  saveNewProductWithImages(product: AdminProductUpdate): Observable<AdminProductUpdate>{
    console.log(product)
    return this.http.post<AdminProductUpdate>("/api/v1/admin/trips",product)
  }
}
