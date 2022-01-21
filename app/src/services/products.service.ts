import { Injectable } from '@angular/core';
import { nextTick } from 'process';


import { environment } from '../environments/environment';
import * as Walmart from 'walmart';
const walmart = Walmart(environment.WALMART_API_KEY);



@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor() {
    console.log(environment.WALMART_API_KEY)
   }

  // register(product : Product) {

  // }
}
