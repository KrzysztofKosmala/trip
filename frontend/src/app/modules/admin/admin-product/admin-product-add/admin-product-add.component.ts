import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminMessagesService } from '../../common/service/admin-messages.service';
import { AdminProductPopupImagesListComponent } from '../admin-product-popup-images-list/admin-product-popup-images-list.component';
import { AdminProductAddService } from './admin-product-add.service';

@Component({
  selector: 'app-admin-product-add',
  templateUrl: './admin-product-add.component.html',
  styleUrls: ['./admin-product-add.component.scss']
})
export class AdminProductAddComponent implements OnInit {
  
  productForm!: FormGroup;
  constructor(public dialog: MatDialog, private formBuilder: FormBuilder, private adminProductAddService: AdminProductAddService, private router: Router, private snackBar: MatSnackBar, private adminMessageService: AdminMessagesService) { }
  showComponent = false;
  dialogRef: MatDialogRef<AdminProductPopupImagesListComponent> | null = null;

  openPopup() {
    this.dialogRef = this.dialog.open(AdminProductPopupImagesListComponent, {

maxHeight: '70vh',
maxWidth: '70vw',
height: '80vh',
width:'80vw',
      autoFocus: false
    });

    this.dialogRef.afterClosed().subscribe(result => {
      console.log('Popup został zamknięty.');
    });
  }
  showImagesPopup() {
    this.showComponent = true;
  }
  closeImagesPopup() {
    this.showComponent = false;
  }
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
      wifi:[false]
    })
  }

  submit(){
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
