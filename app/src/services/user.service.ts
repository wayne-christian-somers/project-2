import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../models/user';

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor (private http: HttpClient) {}

  register(user : User) {
    return this.http.post('/user/register', user)
  }

  login(user: User) {
   return this.http.post('/user/login', user);
  }
}
