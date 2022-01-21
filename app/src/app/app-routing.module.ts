
import { RegisterComponent } from './components/register/register.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { CartComponent } from './components/cart/cart.component';

const routes: Routes = [

   {path: '', redirectTo: 'welcome', pathMatch: 'full'},
   {path: 'welcome', component: MainComponent},
   {path: 'cart', component: CartComponent},
   {path: 'register', component: RegisterComponent},
   {path: 'login', component: LoginComponent},
   {path: '**', component: AppComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
