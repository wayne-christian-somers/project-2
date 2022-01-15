import { addUser } from './../store/user/user.actions';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { timingSafeEqual } from 'crypto';
import * as fromStore from '../store/user/user.reducer';
import { stringify } from 'querystring';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private store: Store) { }

  ngOnInit(): void {
  }

  // method called when "register" button is pressed in UI
  onSubmit(email: string, password: string) {
    this.store.dispatch(addUser({email: email, password: password}));
  }
}
