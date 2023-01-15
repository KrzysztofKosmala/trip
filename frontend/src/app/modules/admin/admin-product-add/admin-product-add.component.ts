import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminMessagesService } from '../admin-messages/admin-messages.service';
import { AdminProductAddService } from './admin-product-add.service';

@Component({
  selector: 'app-admin-product-add',
  templateUrl: './admin-product-add.component.html',
  styleUrls: ['./admin-product-add.component.scss']
})
export class AdminProductAddComponent implements OnInit {
  
  productForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private adminProductAddService: AdminProductAddService, private router: Router, private snackBar: MatSnackBar, private adminMessageService: AdminMessagesService) { }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      desc:['', [Validators.required, Validators.minLength(4)]],
      category:['', [Validators.required]],
      basePrice:['', [Validators.required, Validators.min(0)]],
      currency:['PLN', Validators.required]
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
