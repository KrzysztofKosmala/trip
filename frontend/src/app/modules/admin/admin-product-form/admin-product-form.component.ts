import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-admin-product-form',
  templateUrl: './admin-product-form.component.html',
  styleUrls: ['./admin-product-form.component.scss']
})
export class AdminProductFormComponent implements OnInit {

  @Input() parentForm!: FormGroup;

  ngOnInit(): void {
  }

  get name()
  {
    return this.parentForm.get("name");
  }
  get desc()
  {
    return this.parentForm.get("desc");
  }
  get category()
  {
    return this.parentForm.get("category");

  }
  get basePrice()
  {
    return this.parentForm.get("basePrice");
  }
  get currency()
  {
    return this.parentForm.get("currency");
  }
  get slug()
  {
    return this.parentForm.get("slug");
  }
}
