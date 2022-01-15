import { UserService } from './../../../../services/user.service';
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap, mergeMap } from 'rxjs/operators';
import { Observable, EMPTY, of } from 'rxjs';

import * as UserActions from './user.actions';
import { User } from 'src/models/user';
import { off } from 'process';



@Injectable()
export class UserEffects {

    constructor(private userService: UserService, private actions$: Actions) { }

  login$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(UserActions.login),
      concatMap((action) => {
        let userObject = new User(action.email, action.password);
        return this.userService.login(userObject).pipe(
            map(data => UserActions.loginSuccess({ data })),
            catchError(error => of(UserActions.loginFailure({ error }))))
         } )

      )
        })


  register$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(UserActions.register),
      concatMap((action) => {
        let userObject = new User(action.email, action.password);
        return this.userService.register(userObject).pipe(
          map(data => UserActions.registerSuccess({ data })),
          catchError(error => of(UserActions.registerFailure({ error })))
        )
      })
    )
  })


  loadUsers$ = createEffect(() => {
    return this.actions$.pipe(

      ofType(UserActions.loadUsers),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => UserActions.loadUsersSuccess({ data })),
          catchError(error => of(UserActions.loadUsersFailure({ error }))))
      )
    );
  });





}
