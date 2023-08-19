import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDetails } from '../product-detalis/model/ProductDetails';
import { ProductDetailsService } from '../product-detalis/product-details.service';
import { InitData } from './model/InitData';
import { OrderDto } from './model/OrderDto';
import { OrderSummary } from './model/OrderSummary';
import { OrderService } from './order.service';
import { JwtService } from '../common/service/jwt.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProfileService } from '../profile/profile.service';
import { UserDetails } from '../profile/model/userDetails';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit{

formGrup!: FormGroup;
orderSummary!: OrderSummary;
product!: ProductDetails;
userDetails!: UserDetails;
initData!: InitData;
isLoggedIn = false;
constructor
  (
    private productDetailsService: ProductDetailsService,
    private formBuilder: FormBuilder,
    private orderService: OrderService, 
    private router: ActivatedRoute,
    private jwtService: JwtService,
    private routerNavigation: Router,
    private snackBar: MatSnackBar,
    private profileService: ProfileService
  ){}

  ngOnInit(): void 
  {
    this.productDetailsService.canUserOrder().subscribe(result => {
      console.log(result)
      if(!result)
      {
        this.routerNavigation.navigate(['/profile/update']).then(()=> this.snackBar.open("Uzupełnij dane aby zarezerwować." ,"", {duration: 3000}));
      }
    });

    this.profileService.getDetails().subscribe(details => this.userDetails = details);

      const slug = this.router.snapshot.params['slug'];
      this.productDetailsService.getProductDetails(slug).subscribe(product => this.product = product);
      this.formGrup = this.formBuilder.group({
        firstname: [''],
        lastname: [''],
        street: [''],
        zipcode: [''],
        city: [''],
        email: [''],
        phone: [''],
        payment: ['']
      });
      this.getinitData();
      this.isLoggedIn = this.jwtService.isLoggedIn();
    

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
    paymentId: Number(this.formGrup.get('payment')?.value.id),
  } as OrderDto).subscribe(orderSummary => {
    this.orderSummary=orderSummary;

  })
}

getinitData()
{
  this.orderService.getInitData()
  .subscribe(initData => {
    this.initData =initData;
    console.log(initData)
    this.setDefaultPayment();
  })
}
  setDefaultPayment() {
    this.formGrup.patchValue({"payment": this.initData.payments.filter(payment => payment.defaultPayment === true)[0]})
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
