import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';








@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) {

   }






   getProductsByKeyword(searchTerm: string) {
    // let walmartHeaders = this.walmartUtil.generateWalmartAuthHeaders(environment.WALMART_CONSUMER_ID, "1", Buffer.from(environment.WALMART_PRIVATE_KEY, 'base64').toString())

    // let walmartHttpOptions = {
    //   headers: new HttpHeaders({
    //     'WM_SEC.KEY_VERSION': '1',
    //     "WM_CONSUMER.ID": walmartHeaders['WM_CONSUMER.ID'],
    //     "WM_CONSUMER.INTIMESTAMP": walmartHeaders['WM_CONSUMER.INTIMESTAMP'].toString(),
    //     "WM_SEC.AUTH_SIGNATURE": walmartHeaders['WM_SEC.AUTH_SIGNATURE'],

    //   })
    // }

    //  let url : string = "https://developer.api.walmart.com/api-proxy/service/affil/product/v2/search?"
    let url : string = "https://developer.api.walmart.com/api-proxy/service/affil/product/v2/trends"
     return this.http.get(url);
   }


}
