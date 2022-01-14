import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../models/user';

@Injectable({ providedIn: 'root' })
export class loginService {

  constructor (private http: HttpClient) {}
  login() {
   return this.http.post('/login', new User());
  }
}
