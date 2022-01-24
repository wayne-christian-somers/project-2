import { AddHeaderInterceptor } from 'config/config.headerInterceptor';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


import { User } from '../app/components/store/user/user.reducer'
import { baseURL } from 'config/config.service';
``
@Injectable({ providedIn: 'root' })
export class UserService {
  static loginSuccess(loginSuccess: any): import("rxjs").OperatorFunction<import("@ngrx/store").Action, any> {
    throw new Error('Method not implemented.');
  }






  constructor (private http: HttpClient) {}

  register(user : User) {
    console.log("this is user data in register method");
    console.log(user);
    return this.http.post(baseURL + 'user/register', user, {responseType: 'text' as 'json'})
  }

  login(user: User) {
   return this.http.post(baseURL + 'user/login', user, {responseType: 'text' as 'json'});
  }

  test(user: User) {
    return this.http.patch(baseURL + 'user/login', user, {responseType: 'text' as 'json'});
  }

}
