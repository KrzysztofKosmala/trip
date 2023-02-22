import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { SidebarService } from 'src/app/shared/components/sidebar/sidebar.service';
import { Page } from 'src/app/shared/model/page';
import { Filter } from './filter';
import { Trip } from '../common/model/trip';
import { ProductService } from './product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {


  formValues: any;
  page!: Page<Trip>;
  filter = new Filter();
  constructor(private productService: ProductService, private sidebarService: SidebarService) { }

  ngOnInit(): void {
    this.getTrips()
    this.sidebarService.formValues$.subscribe(formValues => {
      this.updateFilter(formValues);
      this.getTripsByFilter(this.filter);
      console.log(this.filter)
    });
  }

  private updateFilter(formValues: any): void {
    this.filter.destination = formValues.destination;
    this.filter.slopNearby = formValues.slopNearby;
    this.filter.apartment = formValues.apartment;
    this.filter.house = formValues.house;
    this.filter.wifi = formValues.wifi;
    this.filter.food = formValues.food;
    this.filter.spa = formValues.spa;
    
  }


  getTrips()
  {
      this.getTripPage(0,10)
  }

  getTripsByFilter(filter: Filter)
  {
    console.log(filter)
      this.getTripPageByFilter(0,10, filter)
  }

  private getTripPage(page: number, size: number)
  {
    this.productService.getTrips(page, size).subscribe
    (
      page => 
      {
          this.page = page
      }
    );
  }

  private getTripPageByFilter(page: number, size: number, filter: Filter)
  {
    this.productService.getTripsByFilter(page, size, filter).subscribe
    (
      page => 
      {
          this.page = page
      }
    );
  }

  onPageEvent(event: PageEvent): void
  {
    this.getTripPage(event.pageIndex, event.pageSize);
  }
}
