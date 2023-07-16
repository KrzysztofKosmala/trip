import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatTable } from '@angular/material/table';
import { Image } from '../../admin-image/model/Image';
import { AdminProductPopupImagesListComponent } from '../admin-product-popup-images-list/admin-product-popup-images-list.component';
@Component({
  selector: 'app-admin-product-form',
  templateUrl: './admin-product-form.component.html',
  styleUrls: ['./admin-product-form.component.scss']
})
export class AdminProductFormComponent implements OnInit {

  @Input() parentForm!: FormGroup;
  ngOnInit(): void {

  }
  constructor(public dialog: MatDialog, private changeDetectorRef: ChangeDetectorRef){}
  
  countries: string[] = ['PL', 'AU', 'FR'];


  get name()
  {
    return this.parentForm.get("name");
  }
  get desc()
  {
    return this.parentForm.get("desc");
  }
  get destination()
  {
    return this.parentForm.get("destination");

  }

  get basePrice()
  {
    return this.parentForm.get("basePrice");
  }

  get salePrice()
  {
    return this.parentForm.get("salePrice");
  }
  get currency()
  {
    return this.parentForm.get("currency");
  }
  get slug()
  {
    return this.parentForm.get("slug");
  }
  get fullDesc()
  {
    return this.parentForm.get("fullDesc");
  }
  get apartment()
  {
    return this.parentForm.get("apartment");
  }
  get food()
  {
    return this.parentForm.get("food");
  
  }
  get slopNearby()
  {
    return this.parentForm.get("slopNearby");
  }
  get spa()
  {
    return this.parentForm.get("spa");
  }
  get house()
  {
    return this.parentForm.get("house");
  }
  get wifi()
  {
    return this.parentForm.get("wifi");
  }
  get startDate()
  {
    return this.parentForm.get("startDate");
  }
  get endDate()
  {
    return this.parentForm.get("endDate");
  }

}
