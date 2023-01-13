import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AdminProductUpdateService } from '../admin-product-update.service';
import { AdminProductUpdate } from './model/adminProductUpdate';

@Component({
  selector: 'app-admin-product-update',
  templateUrl: './admin-product-update.component.html',
  styleUrls: ['./admin-product-update.component.scss']
})
export class AdminProductUpdateComponent implements OnInit {

  product!: AdminProductUpdate;
  productForm!: FormGroup;
  constructor(
    private router: ActivatedRoute, 
    private adminProductUpdateService: AdminProductUpdateService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar
    ) { }

  ngOnInit(): void {
    this.getProduct();

    this.productForm = this.formBuilder.group({
      name: [''],
      desc:[''],
      category:[''],
      basePrice:[''],
      currency:['PLN']
    })
  }

  submit(){
    let id = Number(this.router.snapshot.params['id']);
    this.adminProductUpdateService.savePost(id, {
        name: this.productForm.get('name')?.value,
        desc: this.productForm.get('desc')?.value,
        category: this.productForm.get('category')?.value,
        basePrice: this.productForm.get('basePrice')?.value,
        currency: this.productForm.get('currency')?.value,
      } as AdminProductUpdate).subscribe(product => {
        this.mapFormValues(product)
        this.snackBar.open("Produkt został zapisany", '', {duration: 3000})
      });
  }

  private mapFormValues(product: AdminProductUpdate): void {
    return this.productForm.setValue({
      name: product.name,
      desc: product.desc,
      category: product.category,
      basePrice: product.basePrice,
      currency: product.currency
    });
  }

  getProduct(){
    let id = Number(this.router.snapshot.params['id']);
    this.adminProductUpdateService.getProduct(id)
    .subscribe(product => this.mapFormValues(product));
  }

}
