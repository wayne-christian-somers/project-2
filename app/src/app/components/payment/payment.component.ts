import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import * as fromStore from '../store/products/products.reducer';
import { FormBuilder } from '@angular/forms';
import { state } from '@angular/animations';
import { Item, Payment, Shipment } from 'src/app/components/store/products/products.reducer';
import { submitPayment } from '../store/products/products.actions';
import { ProductsInCart } from '../store/cart/cart.reducer';
import { Observable } from 'rxjs';
import { selectCartState } from '../store/cart/cart.selectors';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {
  productsInCart$ : Observable<ProductsInCart>;
  productsInCart : any[] = [];


  paymentForm = this.formBuilder.group({
    firstName: [''],
    lastName: [''],
    street: [''],
    company: [''],
    city: [''],
    state: [''],
    zip: [''],
    country: [''],
    phone: [''],
    email: ['']
  });

  constructor(private store: Store, private formBuilder: FormBuilder) {
        this.productsInCart$ = store.select(selectCartState)
        this.productsInCart$.subscribe((productsInCart : ProductsInCart) =>{
      this.productsInCart = productsInCart.productsInCart;
    })
   }

  ngOnInit(): void {
  }

  getFirstName = () => this.paymentForm.get('firstName')?.value || "";

  getLastName = () => this.paymentForm.get('lastName')?.value || "";

  getStreet = () => this.paymentForm.get('street')?.value || "";

  getCompany = () => this.paymentForm.get('company')?.value || "";

  getCity = () => this.paymentForm.get('city')?.value || "";

  getState = () => this.paymentForm.get('state')?.value || "";

  getZip = () => this.paymentForm.get('zip')?.value || "";

  getCountry = () => this.paymentForm.get('country')?.value || "";

  getPhone = () => this.paymentForm.get('phone')?.value || "";

  getEmail = () => this.paymentForm.get('email')?.value || "";


  onSubmit(firstName: string, lastName : string, street: string, company : string, city : string, state : string, zip : string, country : string, phone: string, email : string) {

    const shipmentObject : Shipment = {
      name: firstName + ' ' + lastName,
      company: company,
      street1: street,
      city: city,
      state: state,
      zip: zip,
      country: country,
      phone: phone,
      email: email
    }

    let paymentAmount : number = 0

    const itemsArray : Item[] = []


        this.productsInCart.forEach((product) => {
          paymentAmount += product.salePrice;
          itemsArray.push({name: product.name, amount: product.salePrice})
        }

    );



    const paymentObject : Payment = {
      charge_amount: `${paymentAmount}`,
      nonce: 'fake-valid-visa-nonce',
      deviceData: 'desktop'
    }






  this.store.dispatch(submitPayment({payment: paymentObject, shipment: shipmentObject, items: itemsArray}))
  }
}
