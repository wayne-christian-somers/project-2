import { Component, Input, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { addProductToCart } from '../store/cart/cart.actions';
import { Product } from '../store/products/products.reducer';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})



export class ProductComponent implements OnInit {

  constructor(private store: Store) { }

  ngOnInit(): void {
  }

  @Input() product : any = {};

  addProductToCart() {
    console.log("add to cart button is clicked")
    this.store.dispatch(addProductToCart({product: {}}))
  }

}


