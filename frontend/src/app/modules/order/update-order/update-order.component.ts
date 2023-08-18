import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { OrderService } from '../order.service';
import { ActivatedRoute } from '@angular/router';
import { JwtService } from '../../common/service/jwt.service';
import { OrderDto } from '../model/OrderDto';

@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['./update-order.component.scss']
})
export class UpdateOrderComponent implements OnInit{

  formGrup!: FormGroup;
  isLoggedIn = false;
  orderToEdit!: OrderDto;
  constructor
    (
      private formBuilder: FormBuilder,
      private orderService: OrderService, 
      private router: ActivatedRoute,
      private jwtService: JwtService
    ){}
  ngOnInit(): void 
  {
    const id = this.router.snapshot.params['id'];
    this.formGrup = this.formBuilder.group({
      firstname: [''],
      lastname: ['']
    });
    this.orderService.getOrder(id).subscribe(order => { 
      
     this.mapFormValues(order)
        
      });
      console.log(this.orderToEdit)



    this.isLoggedIn = this.jwtService.isLoggedIn();
  }

  mapFormValues(order: OrderDto): void {
    this.formGrup.setValue(
      {
        firstname: order.firstname,
        lastname: order.lastname
      }
    )
  }

  submit()
  {
    
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
