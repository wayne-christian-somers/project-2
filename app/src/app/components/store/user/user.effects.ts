import { UserService } from './../../../../services/user.service';
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap, mergeMap } from 'rxjs/operators';
import { Observable, EMPTY, of } from 'rxjs';

import * as UserActions from './user.actions';
import { User } from 'src/models/user';



@Injectable()
export class UserEffects {

    constructor(private userService: UserService, private actions$: Actions) { }

  addUser$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(UserActions.addUser),
      concatMap((action) => {
        let userObject = new User(action.email, action.password);
        return this.userService.register(userObject).pipe(
            map(data => UserActions.loadUsersSuccess({ data })),
            catchError(error => of(UserActions.loadUsersFailure({ error }))))
         } )

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
