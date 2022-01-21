import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap } from 'rxjs/operators';
import { Observable, EMPTY, of } from 'rxjs';

import * as ProductsActions from './products.actions';
import { ProductsService } from '../../../../services/products.service';
import { UserService } from '../../../../services/user.service';



@Injectable()
export class ProductsEffects {
constructor(private actions$: Actions, private productsService: ProductsService) {}

  loadProductss$ = createEffect(() =>
   this.actions$.pipe(

      ofType(ProductsActions.loadProductss),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => ProductsActions.loadProductssSuccess({ data })),
          catchError(error => of(ProductsActions.loadProductssFailure({ error }))))
      )
   )
    );

  searchProducts$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ProductsActions.searchProductsByTerm),
      concatMap((action) =>
        this.productsService.getProductsByKeyword(action.searchTerm).pipe(
          map(data => {
            // ProductsActions.addProuct
            return ProductsActions.searchProductsByTermSuccess({ data })}),
          catchError(error => of(ProductsActions.searchProductsByTermFailure({ error }))
        ),
      )
    )
    )
    )
}
