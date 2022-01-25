import { EventEmitter, Injectable } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invokeCartComponentFunction = new EventEmitter();    
  subsVar: Subscription | undefined; 
  constructor() { }

  onCartComponentFunction(){    
    this.invokeCartComponentFunction.emit();    
  } 
}
