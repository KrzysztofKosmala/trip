import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
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
  constructor(public dialog: MatDialog){}
  
  countries: string[] = ['PL', 'AU', 'FR'];
  imagesFromPopup: Image[] = []; 
  displayedColumns: string[] = ['name'];
  openPopup(): void {
    const dialogRef: MatDialogRef<AdminProductPopupImagesListComponent> = this.dialog.open(
      AdminProductPopupImagesListComponent,
      {
        maxHeight: '70vh',
        maxWidth: '70vw',
        height: '80vh',
        width: '80vw',
        autoFocus: false
      }
    );
    dialogRef.componentInstance.destination = this.parentForm.get('destination')?.value; 
    
    dialogRef.componentInstance.selectedImages.subscribe((selectedImages: Image[]) => {
      console.log(selectedImages);
      this.imagesFromPopup = selectedImages;
      // tutaj możesz przypisać wybrane obrazy do zmiennej w AdminProductFormComponent
    });
  }


  handleImageSelection(images: Image[]): void {
    this.imagesFromPopup = images;
  }
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
  get images(): FormArray {
    return this.parentForm.get('images') as FormArray;
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

}
