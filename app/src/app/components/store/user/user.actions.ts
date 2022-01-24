import { createAction, props } from '@ngrx/store';

export const loadUsers = createAction(
  '[User] Load Users'
);

export const loadUsersSuccess = createAction(
  '[User] Load Users Success',
  props<{ data: any }>()
);

export const loadUsersFailure = createAction(
  '[User] Load Users Failure',
  props<{ error: any }>()
);

export const login = createAction(
  '[User] Login User',
  props<{email: string, password: string}>()
);

export const loginSuccess = createAction(
  '[User] Login User Success',
  props<{ data: any }>()
);

export const loginFailure = createAction(
  '[User] Login User Failure',
  props<{ error: any }>()
);

export const register = createAction(
  '[User] Register User',
  props<{email: string, password: string}>()

);

export const registerSuccess = createAction(
  '[User] Register User Success',
  props<{ data: any }>()
);

export const registerFailure = createAction(
  '[User] Register User Failure',
  props<{ error: any }>()
);

