import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


import { environment } from '../environments/environment';
import * as Walmart from 'walmart';
const walmart = Walmart(environment.WALMART_API_KEY);



@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) {
    console.log(environment.WALMART_API_KEY)
   }

       walmartHttpOptions = {
      headers: new HttpHeaders({
        'WM_SEC.KEY_VERSION': '1',
        "WM_CONSUMER.ID": "environment.WALMART_API_KEY",
        "WM_CONSUMER.INTIMESTAMP": Date.now().toString(),
        WM_SEC.AUTH_SIGNATURE

      })
    }

   getProductsByKeyword(searchTerm: string) {
     let url : string = "//api.walmartlabs.com/v1/search?apiKey=" + environment.WALMART_API_KEY + "&query=" + searchTerm;
     return this.http.get(url, this.walmartHttpOptions);
   }


}
