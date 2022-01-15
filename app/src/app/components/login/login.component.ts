import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from '../../../models/user';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loading$: Observable<boolean>;
  users$: Observable<User[]>;
  user$: Observable<User>;

  constructor(private userService: UserService) {
    this.users$ = userService.entities$;
    this.loading$ = userService.loading$;
    this.user$ = userService.;

  }

  ngOnInit() {
    this.getuseres();
  }

  onSubmit(username: string, password: string) {
    store.dispatch(login({ username: username, password: password }));
  }

}
