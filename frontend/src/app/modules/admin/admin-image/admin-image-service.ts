import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Page } from 'src/app/shared/model/page';
import { UploadResponse } from './model/uploadResponse';

@Injectable({
  providedIn: 'root'
})
export class AdminImageService {

  constructor(private http: HttpClient) { }

  uploadFile(image: File): Observable<UploadResponse>{
    const formData = new FormData();
    formData.append('image', image);

    return this.http.post<UploadResponse>('api/v1/admin/images/upload-image', formData)
    .pipe(map(response => {
      return response;
    }));
  }

  getImages(page: number, size: number) : Observable<Page<UploadResponse>>
  {
      return this.http.get<Page<UploadResponse>>(`api/v1/admin/images?page=${page}&size=${size}`);
  }
}
