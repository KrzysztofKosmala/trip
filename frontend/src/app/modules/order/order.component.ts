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
friends: string[] = [];
slug!: string;
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

      this.slug = this.router.snapshot.params['slug'];
      this.productDetailsService.getProductDetails(this.slug).subscribe(product => this.product = product);
      this.formGrup = this.formBuilder.group({
        payment: [''],
        transport: ['']
      });
      this.getinitData();
      this.isLoggedIn = this.jwtService.isLoggedIn();
    

  }
  statuses = new Map<string, string>([
    ["NEW", "Nowe"]
  ])
  cities = new Map<string, string>([
    ["NEW", "Nowe"]
  ])
  transports = new Map<string, string>([
    ["NEW", "Nowe"]
  ])
submit()
{
  this.orderService.placeOrder({
    firstname: this.userDetails.firstname,
    lastname: this.userDetails.lastname,
    street: this.userDetails.street,
    postal: this.userDetails.postal,
    city: this.userDetails.city,
    email: this.userDetails.email,
    phone: this.userDetails.phone,
    productslug: this.product.slug,
    transport: this.formGrup.get('transport')?.value.id,
    friendEmails: this.friends,
    //departureCity: this.formGrup.get('departureCity')?.value,
    paymentId: Number(this.formGrup.get('payment')?.value.id),
  } as OrderDto).subscribe(orderSummary => {
    this.orderSummary=orderSummary;

  }, error => {
    console.log(error.error.message)
  })
}
getinitData()
{
  this.orderService.getInitData()
  .subscribe(initData => {
    this.initData =initData;
    console.log(initData)
    this.setDefaultPayment();
    this.setDefaultTransport();
  })
}
  setDefaultPayment() {
    this.formGrup.patchValue({"payment": this.initData.payments.filter(payment => payment.defaultPayment === true)[0]})
  }
  setDefaultTransport() {
    console.log(this.initData.transports[0].name)
    //this.formGrup.get("transport")?.setValue(this.initData.transports[0])
    //this.formGrup.patchValue({transport: this.initData.transports[0].name})
    this.formGrup.patchValue({transport: this.initData.transports.filter(transport => transport.defaultTransport === true)[0]})
  }


addFriend(email: string)
{
  console.log('Dodaj uczestnika z adresem e-mail: ' + email);
  
if(this.friends.length<6)
 { this.orderService.getRoomMate(email, this.slug).subscribe(response => {
    console.error(response)
    this.friends.push(response.email)
  }, error => {
    console.log(error);
    this.snackBar.open("Nie można dodać znajomego", error, {duration: 3000})
  });
 }else{
  this.snackBar.open("Można dodać maksymalnie 6 znajomych.", "", {duration: 3000})
 }
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
