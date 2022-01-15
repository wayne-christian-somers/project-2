import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../models/user';

@Injectable({ providedIn: 'root' })
export class UserService {

  private baseUrl: string = "http://localhost:4200/"

  constructor (private http: HttpClient) {}

  register(user : User) {
    return this.http.post(this.baseUrl + 'user/register', user)
  }

  login(user: User) {
   return this.http.post(this.baseUrl + 'user/login', user);
  }
}
