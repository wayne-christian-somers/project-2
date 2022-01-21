import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import * as fromStore from '../store';

import { environment } from '../../../environments/environment';
import { addProductToCart } from '../store/cart/cart.actions';
import { addProductToProducts } from '../store/products/products.actions';


// const walmart = require('walmart')(environment.WALMART_API_KEY);

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(private store: Store) { }

  ngOnInit(): void {

  }


  addProductToCart() {
    console.log("add to cart button is clicked")
    this.store.dispatch(addProductToCart({product: {}}))
  }

  addProductToAllProducts() {
    this.store.dispatch(addProductToProducts({product: {}}))
  }


}
