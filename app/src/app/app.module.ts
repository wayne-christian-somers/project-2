import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './components/nav/nav.component';
import { HttpClientJsonpModule, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { reducers, metaReducers } from './components/store';
import { LoginComponent } from './components/login/login.component';
import * as fromUser from './components/store/user/user.reducer';
import { UserEffects } from './components/store/user/user.effects';
import { AddHeaderInterceptor } from 'config/config.headerInterceptor';
import { RegisterComponent } from './components/register/register.component';
import * as fromCart from './components/store/cart/cart.reducer';
import { CartEffects } from './components/store/cart/cart.effects';
import { CartComponent } from './components/cart/cart.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MainComponent } from './components/main/main.component';
import * as fromAuth from './components/store/auth/auth.reducer';
import { AuthEffects } from './components/store/auth/auth.effects';
import * as fromProducts from './components/store/products/products.reducer';
import { ProductsEffects } from './components/store/products/products.effects';
import { ProductsComponent } from './components/products/products.component';
import { ProductComponent } from './components/product/product.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { CartIconComponent } from './components/cart-icon/cart-icon.component';
import { PaymentComponent } from './components/payment/payment.component'
import { EventEmitterService } from 'src/services/event-emitter.service';

 



@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    RegisterComponent,
    CartComponent,
    MainComponent,
    ProductsComponent,
    ProductComponent,
    SearchBarComponent,
    CartIconComponent,
    PaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    !environment.production ? StoreDevtoolsModule.instrument() : [],
    StoreModule.forRoot(reducers, { metaReducers }),
    EffectsModule.forRoot(),
    EffectsModule.forFeature([UserEffects, CartEffects, AuthEffects, ProductsEffects]),
    StoreModule.forFeature(fromCart.cartFeatureKey, fromCart.reducer),
    ReactiveFormsModule,
    StoreModule.forFeature(fromAuth.authFeatureKey, fromAuth.reducer),
    StoreModule.forFeature(fromProducts.productsFeatureKey, fromProducts.reducer)
  ],
  providers: [{
      provide: HTTP_INTERCEPTORS,
      useClass: AddHeaderInterceptor ,
      multi: true,
    },
    EventEmitterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

