import { createAction, props } from '@ngrx/store';
import { Product } from '../products/products.reducer';

export const loadCarts = createAction(
  '[Cart] Load Carts'
);

export const loadCartsSuccess = createAction(
  '[Cart] Load Carts Success',
  props<{ data: any }>()
);

export const loadCartsFailure = createAction(
  '[Cart] Load Carts Failure',
  props<{ error: any }>()
);

export const addProductToCart = createAction(
  '[Cart] Add Cart',
  props<{ product: Product}>()
)

export const emptyCart = createAction(
  '[Cart] Empty Cart' 
)


