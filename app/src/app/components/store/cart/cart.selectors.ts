import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromCart from './cart.reducer';

export const selectCartState = createFeatureSelector<fromCart.ProductsInCart>(
  fromCart.cartFeatureKey
);
