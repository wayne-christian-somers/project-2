import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as fromStore from '../store/cart/cart.reducer';
import { selectCartState } from '../store/cart/cart.selectors';
import { Product, productsFeatureKey } from '../store/products/products.reducer';

@Component({
  selector: 'app-cart-icon',
  templateUrl: './cart-icon.component.html',
  styleUrls: ['./cart-icon.component.scss']
})
export class CartIconComponent implements OnInit {

  productsInCart$ : Observable<fromStore.ProductsInCart>
  numberOfProducts : number = 0;



  constructor(private store: Store) {
    this.productsInCart$ = store.select(selectCartState)

    this.productsInCart$.subscribe((productsInCart) => {

      this.numberOfProducts = productsInCart.productsInCart.length
      console.log("THIS IS PRODUCTS LENGTH in subscribe")
      console.log(productsInCart.productsInCart.length)

    })



  }

  ngOnInit(): void {
  }

  getNumberOfProducts = () : number => this.numberOfProducts;

  getNumberOfProductsMultiLIne() : number {
    return this.numberOfProducts;
  }


}
