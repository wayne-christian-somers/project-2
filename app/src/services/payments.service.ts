import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { baseURL } from 'config/config.service';
import { PaymentBody } from 'src/app/components/store/products/products.reducer';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

  constructor(private http: HttpClient) { }

  submitPayment(payment: PaymentBody) {
    return this.http.post(baseURL + `order/submit`, payment);
  }
}
