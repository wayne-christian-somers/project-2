import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { EventEmitterService } from 'src/services/event-emitter.service';
import * as fromStore from '../store/cart/cart.reducer';
import { selectCartState } from '../store/cart/cart.selectors';
import { Product } from '../store/products/products.reducer';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  productsInCart$ : Observable<fromStore.ProductsInCart>;
  productsInCart : any[] = [];

  
  constructor(private store: Store, private eventEmitterService: EventEmitterService ) { 

    this.productsInCart$ = store.select(selectCartState)

    this.productsInCart$.subscribe((productsInCart : fromStore.ProductsInCart) =>{
      this.productsInCart = productsInCart.productsInCart;
    })
      
      
  }

  ngOnInit(): void {
    if (this.eventEmitterService.subsVar==undefined) {    
      this.eventEmitterService.subsVar = this.eventEmitterService.    
      invokeCartComponentFunction.subscribe((name:string) => {    
        this.total();    
      });    
    }  
  }

  total() : number {
    let sum = 0;
    for (let productInCart of this.productsInCart) {
      sum = sum + productInCart.salePrice; // 1, "string", false
    }
    return sum;
  }
}
