import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SidebarService } from './sidebar.service';
@Component({
selector: 'app-sidebar',
templateUrl: './sidebar.component.html',
styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  form: FormGroup;

destinations = [
  { value: 'PL', viewValue: 'Polska' },
  { value: 'AU', viewValue: 'Austria' },
  { value: 'FR', viewValue: 'Francja' }
];

constructor(private fb: FormBuilder, private sidebarService: SidebarService) 
{
  this.form = this.fb.group({
    destination: null,
    slopNearby: false,
    apartment: false,
    house: false,
    wifi: false,
    food: false,
    spa: false
  });
}

ngOnInit() {this.onValueChanged();}


onValueChanged() {
    this.form.valueChanges.subscribe(val => {
      //this.sidebarService.sendFormValues(val);
      this.sidebarService.sendFormValues(val);
     // console.log(val)
    });
  }
}