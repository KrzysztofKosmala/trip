import { Component, OnInit } from '@angular/core';
import { ProfileService } from './profile.service';
import { OrderListDto } from './model/orderListDto';
import { JwtService } from '../common/service/jwt.service';
import { RoundedRect } from 'chart.js/dist/types/geometric';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  constructor(private profileService: ProfileService, private jwtService:  JwtService, private router: Router){}

  orders!: Array<OrderListDto>
  displayedColumns = ["id", "placeDate", "orderStatus", "grossValue"]

  ngOnInit(): void {
    if(!this.jwtService.isLoggedIn())
    {
        this.router.navigate(["/login"]);
    }
    this.getOrders();
  }

  getOrders(){
    this.profileService.getOrders().subscribe(orders => this.orders =orders)
  }
}
