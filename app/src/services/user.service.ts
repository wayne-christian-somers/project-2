import { AddHeaderInterceptor } from 'config/config.headerInterceptor';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../app/components/store/user/user.reducer'
``
@Injectable({ providedIn: 'root' })
export class UserService {


  private baseUrl: string = "http://localhost:5000/api/"



  constructor (private http: HttpClient) {}

  register(user : User) {
    console.log("this is user data in register method");
    console.log(user);
    return this.http.post(this.baseUrl + 'user/register', user)
  }

  login(user: User) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':  this.baseUrl + '/register',
      withCredentials: 'true'
    });
   return this.http.post(this.baseUrl + 'user/login', user, {headers});
  }

}
