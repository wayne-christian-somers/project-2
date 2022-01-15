import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { register } from '../store/user/user.actions';
import * as fromStore from '../store/user/user.reducer';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(private store: Store) { }

  ngOnInit(): void {
  }

 // method called when "register" button is pressed in UI
 onSubmit(email: string, password: string) {
  this.store.dispatch(register({email: email, password: password}));
}
}
