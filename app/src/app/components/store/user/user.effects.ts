import { HttpHeaders } from '@angular/common/http';
import { UserService } from './../../../../services/user.service';
import { Injectable } from '@angular/core';
import { Actions, createEffect, Effect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap, mergeMap, tap } from 'rxjs/operators';
import { Observable, EMPTY, of } from 'rxjs';

import * as UserActions from './user.actions';
import { Token } from '@angular/compiler';
import {Router} from '@angular/router';




@Injectable()
export class UserEffects {

    constructor(private userService: UserService, private actions$: Actions, private router: Router) { }

  login$ = createEffect(() =>
   this.actions$.pipe(
      ofType(UserActions.login),
      concatMap((action) =>

      this.userService.login({email: action.email, password :action.password}).pipe(
            map(data => {
                          console.log("login successful")
            console.log(data);
              return UserActions.loginSuccess({ data })}),
            catchError(error => of(UserActions.loginFailure({ error }))))
         )

      )
        )


  //  register$ = createEffect(() =>
  //    this.actions$.pipe(
  //      ofType(UserActions.register),
  //      concatMap((action) =>
  //      this.userService.register({email: action.email, password: action.password})
  //       .pipe(
  //         map((response:any) => {
  //           console.log("this is response object")
  //           console.log(response)
  //           const keys = response.headers.keys();
  //           console.log("this is authorization headers")
  //           console.log(keys)
  //           // console.log(response.headers.get("Authorization"))
  //           return UserActions.registerSuccess( response )
  //         })
  //       )
  //      )
  //    )
  //  )


  register$ = createEffect(() =>
     this.actions$.pipe(
      ofType(UserActions.register),
      concatMap((action) =>

       this.userService.register({email: action.email, password: action.password})
       .pipe(
          map((data) => {
            console.log("registration successful")
            console.log(data);
            return UserActions.registerSuccess({ data })}),
          catchError(error => of(UserActions.registerFailure({ error }))
        ),


      )
    )
  )
  )

    // register$ = createEffect(() =>
    //  this.actions$.pipe(
    //   ofType(UserActions.register),
    //  concatMap((action) => this.userService.register({email: action.email, password :action.password})
    //   .pipe(
    //  .map((response: any) => {
    //     console.log(response.headers)
    //   }
    //  )
    //   )

    //  )
    // )
    // )

    navigateToWelcomeOnLogin$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(UserActions.loginSuccess),
        // map((action: UserActions.loginSuccess) => action),
        tap(() => this.router.navigate([`/welcome`]))
      ),
    { dispatch: false }
  );

   navigateToWelcomeOnRegister$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(UserActions.registerSuccess),
        // map((action: UserActions.loginSuccess) => action),
        tap(() => this.router.navigate([`/welcome`]))
      ),
    { dispatch: false }
  );

    // loginSuccess$ = createEffect(() => {
    //   return this.actions$.pipe(
    //     ofType(UserActions.loginSuccess),
    //     tap(() => {
    //       this.router.navigate(['/welcome'])
    //     })
    //   )
    // })


    // registerSuccess$ = createEffect(() => {
    //   return this.actions$.pipe(
    //     ofType(UserActions.registerSuccess),
    //     tap(() => {
    //       this.router.navigate(['/welcome'])
    //     })
    //   )
    // })
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
