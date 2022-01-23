import { Action, createReducer, on } from '@ngrx/store';
import * as ProductsActions from './products.actions';
import fakeProducts from '../../../../assets/fakeProducts.json'
export const productsFeatureKey = 'products';

export interface Product {

}

export interface Products {
  products: Product[]
}

export const initialState: Products = {
  products: fakeProducts.items

};

export const reducer = createReducer(
  initialState,

  on(ProductsActions.loadProductss, state => state),
  on(ProductsActions.loadProductssSuccess, (state, action) => state),
  on(ProductsActions.loadProductssFailure, (state, action) => state),

  on(ProductsActions.addProductToProducts, (state, { product }) => {
    const newArrayWithAddedProduct = [...state.products, product]
    const newProductsObject = {
      products: newArrayWithAddedProduct
    }
    return newProductsObject
  })

);
