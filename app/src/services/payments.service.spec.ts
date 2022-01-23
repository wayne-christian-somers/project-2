import { TestBed } from '@angular/core/testing';
import { map } from 'rxjs/operators';
import { Item, Payment, Shipment } from 'src/app/components/store/products/products.reducer';

import { PaymentsService } from './payments.service';

describe('PaymentsService', () => {
  let service: PaymentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaymentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

describe('PaymentsService', () => {

 let service: PaymentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaymentsService);
  });


const item : Item = {
    name:   "Bag 'O Stuff",
    amount: 32.51
}

const payment : Payment = {
    charge_amount: "62432",
    nonce:         "aslgiuh9aw8ds",
    deviceData:    "desktop"
}

const shipment : Shipment = {
    name:    "Jim Bob",
    company: "Jim Bob R Us",
    street1: "5432 Postgres Road",
    city:    "AmazonCity",
    state:   "AR",
    zip:     "72202",
    country: "USA",
    phone:   "5012234321",
    email:   "jimbob@gmail.com"
}

const paymentBody = {
    payment:  payment,
    shipment: shipment,
    items:    [item],
}

  it('should be created', () => {
    service.submitPayment(paymentBody)
      .pipe(
        map((response: any) => {
          console.log("this is data from response from backend")
          console.log(response)
          // console.log(response.headers.keys())
        })
      )
  });
})
