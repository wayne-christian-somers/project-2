import { createAction, props } from '@ngrx/store';

export const loadAuths = createAction(
  '[Auth] Load Auths'
);

export const loadAuthsSuccess = createAction(
  '[Auth] Load Auths Success',
  props<{ data: any }>()
);

export const loadAuthsFailure = createAction(
  '[Auth] Load Auths Failure',
  props<{ error: any }>()
);



export const addToken = createAction(
  '[Auth] Add Token',
  props<{ token:string }>()
);

export const addTokenSuccess = createAction(
  '[Auth] Add Token Success',
  props<{ data: any }>()
);

export const addTokenFailure = createAction(
  '[Auth] Add Token Failure',
  props<{ error: any }>()
);
