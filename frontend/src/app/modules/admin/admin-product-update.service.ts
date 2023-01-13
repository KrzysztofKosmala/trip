import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { AdminProductUpdate } from './admin-product-update/model/adminProductUpdate';

@Injectable({
  providedIn: 'root'
})
export class AdminProductUpdateService {



  savePost(id: number, value: any) {
    return this.http.put<AdminProductUpdate>('api/v1/admin/trips/' + id, value)
  }

  constructor(private http: HttpClient) { }

  getProduct(id: number): Observable<AdminProductUpdate>{
    return this.http.get<AdminProductUpdate>("/api/v1/admin/trips/" + id)
  }
}
