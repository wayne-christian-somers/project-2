import { HttpHeaders } from '@angular/common/http';
import { UserService } from './../../../../services/user.service';
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap, mergeMap } from 'rxjs/operators';
import { Observable, EMPTY, of } from 'rxjs';

import * as UserActions from './user.actions';



@Injectable()
export class UserEffects {

    constructor(private userService: UserService, private actions$: Actions) { }

  login$ = createEffect(() =>
   this.actions$.pipe(
      ofType(UserActions.login),
      concatMap((action) =>

      this.userService.login({email: action.email, password :action.password}).pipe(
            map(data => UserActions.loginSuccess({ data })),
            catchError(error => of(UserActions.loginFailure({ error }))))
         )

      )
        )


  register$ = createEffect(() =>
     this.actions$.pipe(
      ofType(UserActions.register),
      concatMap((action) =>

       this.userService.register({email: action.email, password :action.password}).pipe(
          map(data => {
            console.log("THIS IS OUR DATA OBJECT")
            console.log(data)
            // console.log("THIS IS OUR AUTHORIZATION HEADER")
            // console.log(data.headers.keys())
            return UserActions.registerSuccess({ data })
          }),
          catchError(error => {
            console.log("THIS IS OUR ERROR OBJECT")
            console.log(error)
            return of(UserActions.registerFailure({ error }))
          })
        )
      )
    )
  )


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
