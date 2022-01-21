import { createAction, props } from '@ngrx/store';
import { Product } from './products.reducer';

export const loadProductss = createAction(
  '[Products] Load Products'
);

export const loadProductssSuccess = createAction(
  '[Products] Load Products Success',
  props<{ data: any }>()
);

export const loadProductssFailure = createAction(
  '[Products] Load Products Failure',
  props<{ error: any }>()
);

export const searchProductsByTerm = createAction(
  '[Products] Search Products By Term',
  props<{ searchTerm: string }>()
);

export const searchProductsByTermSuccess  = createAction(
  '[Products] Search Products By Term Success',
  props<{ data: any }>()
);

export const searchProductsByTermFailure  = createAction(
  '[Products] Search Products By Term Failure',
  props<{ error: any }>()
);

export const addProductToProducts = createAction(
  '[Products] Add Product to Products',
  props<{product: Product}>()
);

// export const addProduct = createAction(
//   '[Products] Add Product',
//   props<{ Pr}>
// )
