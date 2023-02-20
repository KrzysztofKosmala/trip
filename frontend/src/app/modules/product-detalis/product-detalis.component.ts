import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ProductDetails } from './model/ProductDetails';
import { ProductDetailsService } from './product-details.service';

@Component({
  selector: 'app-product-detalis',
  templateUrl: './product-detalis.component.html',
  styleUrls: ['./product-detalis.component.scss']
})
export class ProductDetalisComponent implements OnInit {

  product!: ProductDetails;
  reviewForm!: FormGroup;

  constructor(private productDetailsService: ProductDetailsService, private router: ActivatedRoute, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getProductDetails();
    this.reviewForm = this.formBuilder.group({
      authorName: ['', [Validators.required, Validators.minLength(2)]],
      content: ['', [Validators.required, Validators.minLength(2)]] 
    })
  }

  getProductDetails(){
    let slug = this.router.snapshot.params["slug"];
    this.productDetailsService.getProductDetails(slug).subscribe(product => this.product = product);
  }
  submit()
  {

  }

  get authorName()
  {
    return this.reviewForm.get('authorName');
  }
  get content()
  {
    return this.reviewForm.get('content');
  }
}
