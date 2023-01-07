import { Injectable } from '@angular/core';
import { Product } from './model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor() { }

  getProducts() : Product[]
  {
      return[
          {
            name: "p1",
            category: "p1",
            desc: "p1",
            price: 10,
            currency: "PLN"
          },
          {
            name: "p2",
            category: "p2",
            desc: "p2",
            price: 10,
            currency: "PLN"
          },
          {
            name: "p3",
            category: "p3",
            desc: "p3",
            price: 10,
            currency: "PLN"
          },
      ]
  }
}
