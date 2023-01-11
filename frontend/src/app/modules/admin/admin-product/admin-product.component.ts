import { Component, OnInit } from '@angular/core';
import { AdminProduct } from './adminProduct';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.scss']
})
export class AdminProductComponent implements OnInit {

  dataSource: AdminProduct[]= [];
  displayedColumns: string[] = ["id", "name", "price"];

  constructor() { }

  ngOnInit(): void {
  }

}
