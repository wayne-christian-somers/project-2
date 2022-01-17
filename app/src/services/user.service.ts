import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../app/components/store/user/user.reducer'
``
@Injectable({ providedIn: 'root' })
export class UserService {

  private baseUrl: string = "http://localhost:5000/"

  constructor (private http: HttpClient) {}

  register(user : User) {
    console.log("this is user data in register method");
    console.log(user);
    return this.http.post(this.baseUrl + 'user/register', user)
  }

  login(user: User) {
   return this.http.post(this.baseUrl + 'user/login', user);
  }

}
