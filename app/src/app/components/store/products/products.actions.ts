import { createAction, props } from '@ngrx/store';

export const loadProductss = createAction(
  '[Products] Load Productss'
);

export const loadProductssSuccess = createAction(
  '[Products] Load Productss Success',
  props<{ data: any }>()
);

export const loadProductssFailure = createAction(
  '[Products] Load Productss Failure',
  props<{ error: any }>()
);
