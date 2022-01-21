import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import * as fromStore from '../store';

import { environment } from '../../../environments/environment';


// const walmart = require('walmart')(environment.WALMART_API_KEY);

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(private store: Store) { }

  ngOnInit(): void {
    console.log(environment.WALMART_CONSUMER_ID)
    console.log(environment.WALMART_PRIVATE_KEY)
  }

}
