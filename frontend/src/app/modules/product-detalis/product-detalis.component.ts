import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ProductDetails } from './model/ProductDetails';
import { ProductDetailsService } from './product-details.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { JwtService } from '../common/service/jwt.service';
@Component({
  selector: 'app-product-detalis',
  templateUrl: './product-detalis.component.html',
  styleUrls: ['./product-detalis.component.scss']
})
export class ProductDetalisComponent implements OnInit {

  product!: ProductDetails;
  reviewForm!: FormGroup;

  constructor(private jwtService: JwtService, private routerNavigation: Router, private snackBar: MatSnackBar, private productDetailsService: ProductDetailsService, private router: ActivatedRoute, private formBuilder: FormBuilder) { }

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
    if(this.jwtService.isLoggedIn())
    {
      this.routerNavigation.navigate(['/order', this.product.slug]);
    }else{
      this.routerNavigation.navigate(['/login']).then(()=> this.snackBar.open("Zaloguj sie aby zarezerwować." ,"", {duration: 3000}));
    }
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
