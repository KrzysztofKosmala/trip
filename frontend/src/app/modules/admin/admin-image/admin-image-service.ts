import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Page } from 'src/app/shared/model/page';
import { Image } from './model/Image';
import { UploadResponse } from './model/uploadResponse';

@Injectable({
  providedIn: 'root'
})
export class AdminImageService {

  constructor(private http: HttpClient) { }

  uploadFile(formData: FormData): Observable<UploadResponse>{

    return this.http.post<UploadResponse>('api/v1/images/upload-image', formData)
    .pipe(map(response => {
      return response;
    }));
  }

  delete(id: number): Observable<void>{
    return this.http.delete<void>('api/v1/images/'+id)
  }

  getImages(page: number, size: number) : Observable<Page<Image>>
  {
      return this.http.get<Page<Image>>(`api/v1/images?page=${page}&size=${size}`);
  }

  getImagesByCountry(page: number, size: number, country: string) : Observable<Page<Image>>
  {
    let params = new HttpParams()
    .set('page', page.toString())
    .set('size', size.toString())
    .set('country', country);

      return this.http.get<Page<Image>>(`api/v1/imagesByCountry`, { params });
  }
}
