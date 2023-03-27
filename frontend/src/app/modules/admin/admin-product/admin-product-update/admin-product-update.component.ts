import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AdminImageService } from '../../admin-image/admin-image-service';
import { AdminMessagesService } from '../../common/service/admin-messages.service';
import { AdminProductUpdateService } from './admin-product-update.service';
import { AdminProductUpdate } from '../model/adminProductUpdate';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AdminProductPopupImagesListComponent } from '../admin-product-popup-images-list/admin-product-popup-images-list.component';
import { Image } from '../../admin-image/model/Image';
import { MatTable } from '@angular/material/table';

@Component({
  selector: 'app-admin-product-update',
  templateUrl: './admin-product-update.component.html',
  styleUrls: ['./admin-product-update.component.scss']
})
export class AdminProductUpdateComponent implements OnInit {
  productImages: Image[] = []; 
  product!: AdminProductUpdate;
  productForm!: FormGroup;
  displayedColumns: string[] = ['data', 'name', 'desc', 'destination','actions'];
  @ViewChild(MatTable) table?: MatTable<any>;
  constructor(
    private router: ActivatedRoute, 
    public dialog: MatDialog, 
    private adminProductUpdateService: AdminProductUpdateService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private adminMessageService: AdminMessagesService,
    private adminImageService: AdminImageService
    ) { }

    
  ngOnInit(): void {
    this.getProduct();

    this.productForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      desc:['', [Validators.required, Validators.minLength(4)]],
      destination:['', [Validators.required]],
      basePrice:['', [Validators.required, Validators.min(0)]],
      currency:['PLN', Validators.required],
      slug: ['', [Validators.required, Validators.minLength(4)]],
      fullDesc: ['', [Validators.minLength(4)]],
      startDate: [''],
      endDate: [''],
      food:[],
      apartment:[],
      slopNearby:[],
      house:[],
      spa:[],
      wifi:[]
    })
  }

  

  removeImage(image: Image): void {
    const index = this.productImages.indexOf(image);
    if (index >= 0) {
        this.productImages.splice(index, 1);
        this.table?.renderRows(); 
    }
    
    console.log(this.productImages)
}

  submit(){
    let id = Number(this.router.snapshot.params['id']);
    this.adminProductUpdateService.savePost(id, {
        name: this.productForm.get('name')?.value,
        desc: this.productForm.get('desc')?.value,
        destination: this.productForm.get('destination')?.value,
        basePrice: this.productForm.get('basePrice')?.value,
        currency: this.productForm.get('currency')?.value,
        slug: this.productForm.get('slug')?.value,
        fullDesc: this.productForm.get('fullDesc')?.value,
        apartment: this.productForm.get('apartment')?.value,
        food: this.productForm.get('food')?.value,
        slopNearby: this.productForm.get('slopNearby')?.value,
        spa: this.productForm.get('spa')?.value,
        house: this.productForm.get('house')?.value,
        wifi: this.productForm.get('wifi')?.value,
        startDate: this.productForm.get('startDate')?.value,
        endDate: this.productForm.get('endDate')?.value,
        images: this.productImages
      } as AdminProductUpdate).subscribe
      (
        {
          next: product => 
          {
            this.mapFormValues(product)
            this.snackBar.open("Produkt został zapisany", '', {duration: 3000})
          },
          error: error => this.adminMessageService.addSpringErrors(error.error),
        });
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
          productImages: this.productImages
        }
      }
    );
    dialogRef.componentInstance.destination = this.productForm.get('destination')?.value; 
    dialogRef.componentInstance.selectedImages.subscribe((selectedImages: Image[]) => {
      this.productImages = selectedImages;
    });
  }


  private mapFormValues(product: AdminProductUpdate): void {

    this.productImages = product.images;
    return this.productForm.setValue({
      name: product.name,
      desc: product.desc,
      destination: product.destination,
      basePrice: product.basePrice,
      currency: product.currency,
      slug: product.slug,
      fullDesc: product.fullDesc,
      apartment: product.apartment,
      food: product.food,
      slopNearby: product.slopNearby,
      spa: product.spa,
      house: product.house,
      startDate: product.startDate,
      endDate: product.endDate,
      wifi: product.wifi
    });
  }

  getProduct(){
    let id = Number(this.router.snapshot.params['id']);
    this.adminProductUpdateService.getProduct(id)
    .subscribe(product => this.mapFormValues(product));
  }

}
