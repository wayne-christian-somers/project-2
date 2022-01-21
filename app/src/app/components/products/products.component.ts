import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as fromStore from '../store/products/products.reducer';
import { Products } from '../store/products/products.reducer';
import { selectProductsState } from '../store/products/products.selectors';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
products$: Observable<fromStore.Products>;
products: fromStore.Product[] = [];

  constructor(private store: Store) {
    this.products$ = store.select(selectProductsState)
    this.products$.subscribe((products : fromStore.Products) => {
      // const newProductsArray = [...this.products, product]
      console.log("a new product  is added to products in products.component.ts")
      this.products = products.products;
    })
   }

  ngOnInit(): void {
  }





}
