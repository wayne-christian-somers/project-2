import { Action, createReducer, on } from '@ngrx/store';

import * as UserActions from './user.actions';

export const userFeatureKey = 'user';


export interface User {
    email: string;
    password: string;
}

export const initialState: User = {
    email: '',
    password: ''
};

export const reducer = createReducer(
  initialState,

  on(UserActions.loadUsers, state => state),
  on(UserActions.loadUsersSuccess, (state, action) => state),
  on(UserActions.loadUsersFailure, (state, action) => state),


  on(UserActions.login, state => state),
  on(UserActions.loginSuccess, (state, action) => state),
  on(UserActions.loginFailure, (state, action) => state),


);
