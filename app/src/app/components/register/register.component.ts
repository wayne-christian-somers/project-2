import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { register } from '../store/user/user.actions';
import * as fromStore from '../store/user/user.reducer';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    registerForm = this.formBuilder.group({
    email: [''],
    password: [''],
  });

  constructor(private store: Store, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  getEmail = () : string => this.registerForm.get('email')?.value || "";


  getPassword = () : string => this.registerForm.get('password')?.value || "";





 // method called when "register" button is pressed in UI
 onSubmit(email: string, password: string) {
   console.log(this.registerForm.value)
  this.store.dispatch(register({email: email, password: password}));
}
}
