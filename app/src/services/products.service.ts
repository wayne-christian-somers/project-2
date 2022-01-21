import { Injectable } from '@angular/core';
import { nextTick } from 'process';

var walmart = require('walmart')(environment.WALMART_API_KEY);

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
