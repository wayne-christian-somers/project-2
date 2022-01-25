import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventEmitterService } from 'src/services/event-emitter.service';
import { Location } from '@angular/common'
@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
  
})
export class NavComponent implements OnInit {

  currentUrl: string = ""
  showNav: boolean = true
  constructor(
    private eventEmitterService: EventEmitterService ,
    private router: Router, 
    private location: Location 
    
  ) { router.events.subscribe((value) => {
    //if (location.path() != ""){
      this.currentUrl = location.path();
      if(location.path() == "/login" || location.path() == "/register") {
        this.showNav = false;
      }else{
        this.showNav = true;
      }
    //}
  })}

  ngOnInit(): void {
    // this.currentUrl = this.router.url;
    // console.log('this is current url')
    // console.log(this.currentUrl)
    
  }

  isCartEmpty() {    
    this.eventEmitterService.onCartComponentFunction();    
  }  
}
