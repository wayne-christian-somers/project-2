
import { login } from './../store/user/user.actions';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import * as fromStore from '../store/user/user.reducer';
import { Observable } from 'rxjs';
import { selectUserState } from '../store/user/user.selectors';
import { User } from '../store/user/user.reducer';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user$: Observable<User>;

      loginForm = this.formBuilder.group({
    email: [''],
    password: [''],
  });
  // there is no reason to load a user store in state if the user
  // hasn't logged in yet, so this is just some example code of basic
  // selector use
  constructor(private store: Store<User>, private formBuilder: FormBuilder) {
      this.user$ = store.select(selectUserState)
  }

  ngOnInit(): void {
  }

    getEmail = () : string => this.loginForm.get('email')?.value || "";


  getPassword = () : string => this.loginForm.get('password')?.value || "";

  // method called when "login" button is pressed in UI
  onSubmit(email: string, password: string) {
    this.store.dispatch(login({email: email, password: password}));
  }
}
