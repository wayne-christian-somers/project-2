import { Action, createReducer, on } from '@ngrx/store';
import { Product } from '../products/products.reducer';
import * as CartActions from './cart.actions';

export const cartFeatureKey = 'cart';

export interface ProductsInCart {
  productsInCart: Product[]
}

export const initialState: ProductsInCart = {
  productsInCart: [{}, {}, {}]
};

export const reducer = createReducer(
  initialState,

  on(CartActions.loadCarts, state => state),
  on(CartActions.loadCartsSuccess, (state, action) => state),
  on(CartActions.loadCartsFailure, (state, action) => state),

);
