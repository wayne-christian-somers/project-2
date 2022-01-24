import { Action, createReducer, on } from '@ngrx/store';
import * as ProductsActions from './products.actions';
// import fakeProducts from '../../../../assets/fakeProducts.json'
export const productsFeatureKey = 'products';
import betterJson from '../../../../assets/betterJson.json'

export interface Product {

}

export interface Products {
  products: Product[]
}

export const initialState: Products = {
  products: betterJson.products,
};

export interface PaymentBody {
    payment:  Payment;
    shipment: Shipment;
    items:    Item[];
}

export interface Item {
    name:   string;
    amount: number;
}

export interface Payment {
    charge_amount: string;
    nonce:         string;
    deviceData:    string;
}

export interface Shipment {
    name:    string;
    company: string;
    street1: string;
    city:    string;
    state:   string;
    zip:     string;
    country: string;
    phone:   string;
    email:   string;
}


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
  }),

  on(ProductsActions.submitPayment, (state, action) => state)

);
