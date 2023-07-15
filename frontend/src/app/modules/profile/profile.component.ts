import { Component, OnInit } from '@angular/core';
import { ProfileService } from './profile.service';
import { OrderListDto } from './model/orderListDto';
import { JwtService } from '../common/service/jwt.service';
import { RoundedRect } from 'chart.js/dist/types/geometric';
import { Router } from '@angular/router';
import { UserDetails } from './model/userDetails';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  constructor(private profileService: ProfileService, private jwtService:  JwtService, private router: Router){}

  orders!: Array<OrderListDto>
  details!: UserDetails
  displayedColumns = ["id", "placeDate", "orderStatus", "grossValue"]

  ngOnInit(): void {
    if(!this.jwtService.isLoggedIn())
    {
        this.router.navigate(["/login"]);
    }
    this.getDetails();
    this.getOrders();
    console.log(this.orders)
  }

  getOrders(){
    this.profileService.getOrders().subscribe(orders => this.orders =orders)
  }
  getDetails(){
    this.profileService.getDetails().subscribe(details => {console.log( details); this.details = details})
  }
}
