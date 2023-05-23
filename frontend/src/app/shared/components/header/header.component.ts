import { Component, OnInit } from '@angular/core';
import { JwtService } from 'src/app/modules/common/service/jwt.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit 
{

  isLoggedIn = false;
  title = "Trip";
  constructor(private jwtService: JwtService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.jwtService.isLoggedIn();
  }

}
