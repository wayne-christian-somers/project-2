import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as fromStore from '../store/cart/cart.reducer';
import { selectCartState } from '../store/cart/cart.selectors';
import { Product } from '../store/products/products.reducer';

@Component({
  selector: 'app-cart-icon',
  templateUrl: './cart-icon.component.html',
  styleUrls: ['./cart-icon.component.scss']
})
export class CartIconComponent implements OnInit {

  products$ : Observable<fromStore.ProductsInCart>
  numProducts : number = 0;
  constructor(private store: Store) {
    this.products$ = store.select(selectCartState)
    
    this.products$.subscribe((product : fromStore.ProductsInCart) => {
      this.products$.
    })


  }

  ngOnInit(): void {
  }

}
