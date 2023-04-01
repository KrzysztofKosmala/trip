import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ProductDetails } from '../product-detalis/model/ProductDetails';
import { ProductDetailsService } from '../product-detalis/product-details.service';
import { OrderDto } from './model/OrderDto';
import { OrderSummary } from './model/OrderSummary';
import { OrderService } from './order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit{

formGrup!: FormGroup;
orderSummary!: OrderSummary;
product!: ProductDetails;
constructor
  (
    private productDetailsService: ProductDetailsService,
    private formBuilder: FormBuilder,
    private orderService: OrderService, 
    private router: ActivatedRoute
  ){}

  ngOnInit(): void 
  {
    const slug = this.router.snapshot.params['slug'];
    this.productDetailsService.getProductDetails(slug).subscribe(product => this.product = product);
    this.formGrup = this.formBuilder.group({
      firstname: [''],
      lastname: [''],
      street: [''],
      zipcode: [''],
      city: [''],
      email: [''],
      phone: ['']
    });
  }

  private statuses = new Map<string, string>([
    ["NEW", "Nowe"]
  ])

submit()
{
  this.orderService.placeOrder({
    firstname: this.formGrup.get('firstname')?.value,
    lastname: this.formGrup.get('lastname')?.value,
    street: this.formGrup.get('street')?.value,
    zipcode: this.formGrup.get('zipcode')?.value,
    city: this.formGrup.get('city')?.value,
    email: this.formGrup.get('email')?.value,
    phone: this.formGrup.get('phone')?.value,
    productslug: this.product.slug,
  } as OrderDto).subscribe(orderSummary => {
    this.orderSummary=orderSummary;

  })
}

getStatus(status: string)
{
  return this.statuses.get(status);
}
get firstname(){
  return this.formGrup.get("firstname")
}

get lastname(){
  return this.formGrup.get("lastname")
}

get street(){
  return this.formGrup.get("street")
}

get zipcode(){
  return this.formGrup.get("zipcode")
}

get city(){
  return this.formGrup.get("city")
}

get email(){
  return this.formGrup.get("email")
}

get phone(){
  return this.formGrup.get("phone")
}

}
