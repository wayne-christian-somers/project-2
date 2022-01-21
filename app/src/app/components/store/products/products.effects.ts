import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap } from 'rxjs/operators';
import { Observable, EMPTY, of } from 'rxjs';

import * as ProductsActions from './products.actions';



@Injectable()
export class ProductsEffects {

  loadProductss$ = createEffect(() => {
    return this.actions$.pipe( 

      ofType(ProductsActions.loadProductss),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => ProductsActions.loadProductssSuccess({ data })),
          catchError(error => of(ProductsActions.loadProductssFailure({ error }))))
      )
    );
  });



  constructor(private actions$: Actions) {}

}
