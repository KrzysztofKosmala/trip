import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Page } from 'src/app/shared/model/page';
import { Trip } from './model/trip';
import { ProductService } from './product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {


  
  page!: Page<Trip>;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getTrips()
  }

  getTrips()
  {
      this.getTripPage(0,10)
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

  onPageEvent(event: PageEvent): void
  {
    this.getTripPage(event.pageIndex, event.pageSize);
  }
}
