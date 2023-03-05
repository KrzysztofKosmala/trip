import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTable } from '@angular/material/table';
import { Router } from '@angular/router';
import { Page } from 'src/app/shared/model/page';
import { AdminImageService } from '../../admin-image/admin-image-service';
import { Image } from '../../admin-image/model/Image';
import { AdminMessagesService } from '../../common/service/admin-messages.service';
import { AdminProductPopupImagesListComponent } from '../admin-product-popup-images-list/admin-product-popup-images-list.component';
import { AdminProductAddService } from './admin-product-add.service';

@Component({
  selector: 'app-admin-product-add',
  templateUrl: './admin-product-add.component.html',
  styleUrls: ['./admin-product-add.component.scss']
})
export class AdminProductAddComponent implements OnInit {
  imagesFromPopup: Image[] = []; 
  imageIds: number[] = []; 
  displayedColumns: string[] = ['data', 'name', 'desc', 'destination','actions'];
  @Output() imagesFromPopupChange: EventEmitter<Image[]> = new EventEmitter();
  productForm!: FormGroup;
  @ViewChild(MatTable) table?: MatTable<any>;
  constructor
  (
    public adminImageService: AdminImageService,
    public dialog: MatDialog, 
    private formBuilder: FormBuilder, 
    private adminProductAddService: AdminProductAddService, 
    private router: Router, 
    private snackBar: MatSnackBar, 
    private adminMessageService: AdminMessagesService
    ) { }


  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      desc:['', [Validators.required, Validators.minLength(4)]],
      destination:[''],
      basePrice:['', [Validators.required, Validators.min(0)]],
      currency:['PLN', Validators.required],
      slug: ['', [Validators.required, Validators.minLength(4)]],
      fullDesc: ['', [Validators.minLength(4)]],
      food:[false],
      apartment:[false],
      slopNearby:[false],
      house:[false],
      spa:[false],
      wifi:[false],
      images: [[]] 
    })
  }

  openPopup(): void {
    const dialogRef: MatDialogRef<AdminProductPopupImagesListComponent> = this.dialog.open(
      AdminProductPopupImagesListComponent,
      {
        maxHeight: '70vh',
        maxWidth: '70vw',
        height: '80vh',
        width: '80vw',
        autoFocus: false,
        data: {
          imagesFromPopup: this.imagesFromPopup
        }
      }
    );
    dialogRef.componentInstance.destination = this.productForm.get('destination')?.value; 
    dialogRef.componentInstance.selectedImages.subscribe((selectedImages: Image[]) => {
      this.imagesFromPopup = selectedImages;
       this.imageIds = this.imagesFromPopup.map(image => image.id);
    });

    
  }

  removeImage(image: Image): void {
    const index = this.imagesFromPopup.indexOf(image);
    if (index >= 0) {
        this.imagesFromPopup.splice(index, 1);
        this.table?.renderRows(); 
    }
    
    console.log(this.imagesFromPopup)
}




  submit(){

    this.imageIds = this.imagesFromPopup.map(image => image.id);
    this.productForm.patchValue({
      images: this.imageIds.map(id => ({ id: id }))
    });
    console.log(this.productForm.value
      )
    this.adminProductAddService.saveNewProduct(this.productForm.value)
    .subscribe(
      {
        next: product => 
        {   
          this.router.navigate(["/admin/products/update", product.id])
          .then(()=> this.snackBar.open("Produkt został dodany","", {duration: 3000}))
        },
        error: error => this.adminMessageService.addSpringErrors(error.error),
      }
      );
  }

}
