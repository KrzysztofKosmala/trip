import { Component, OnInit } from '@angular/core';
import { HomePageDto } from './dto/homePageDto';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  homePageData!: HomePageDto;
  constructor(private homeService: HomeService) { }

  ngOnInit(): void {
    this.getHomePageData();
  }

  getHomePageData()
  {
    this.homeService.getHomePageData()
    .subscribe(homePageData => {
      console
      this.homePageData = homePageData
    });
  }
}
