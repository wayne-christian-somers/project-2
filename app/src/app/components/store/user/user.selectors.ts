import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromUser from './user.reducer';



export const selectUserState = createFeatureSelector<fromUser.User>(
  fromUser.userFeatureKey
);


