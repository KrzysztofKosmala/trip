import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AdminImageService } from '../../admin-image/admin-image-service';
import { AdminMessagesService } from '../../common/service/admin-messages.service';
import { AdminProductUpdateService } from './admin-product-update.service';
import { AdminProductUpdate } from '../model/adminProductUpdate';

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
      food:[],
      apartment:[],
      slopNearby:[],
      house:[],
      spa:[],
      wifi:[]
    })
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
        food: this.productForm.get('apartfoodent')?.value,
        slopNearby: this.productForm.get('slopNearby')?.value,
        spa: this.productForm.get('spa')?.value,
        house: this.productForm.get('house')?.value,
        wifi: this.productForm.get('wifi')?.value,
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

  private mapFormValues(product: AdminProductUpdate): void {
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
      wifi: product.wifi
    });
  }

  getProduct(){
    let id = Number(this.router.snapshot.params['id']);
    this.adminProductUpdateService.getProduct(id)
    .subscribe(product => this.mapFormValues(product));
  }

}
