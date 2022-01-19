import { Action, createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';

export const authFeatureKey = 'auth';

export interface State {
  token: string
}

export const initialState: State = {
  token: ""
};

export const reducer = createReducer(
  initialState,

  on(AuthActions.loadAuths, state => state),
  on(AuthActions.loadAuthsSuccess, (state, action) => state),
  on(AuthActions.loadAuthsFailure, (state, action) => state),

  on(AuthActions.addToken, (state , { token }) => {
    return {...state, token: token}
  }),
  on(AuthActions.addTokenSuccess, (state, action) => state),
  on(AuthActions.addTokenFailure, (state, action) => state),

);
