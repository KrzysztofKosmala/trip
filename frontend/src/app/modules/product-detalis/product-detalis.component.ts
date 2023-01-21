import { Component, OnInit } from '@angular/core';
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


  constructor(private productDetailsService: ProductDetailsService, private router: ActivatedRoute) { }

  ngOnInit(): void {
  }

  getProductDetails(){
    let slug = this.router.snapshot.params["slug"];
    this.productDetailsService.getProductDetails(slug).subscribe(product => this.product = product);
  }

}
