import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SidebarService {

  private formValues = new Subject<any>();

  formValues$ = this.formValues.asObservable();

  sendFormValues(val: any) {
    this.formValues.next(val);
  }
}
