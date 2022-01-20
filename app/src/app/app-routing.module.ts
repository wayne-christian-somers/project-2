
import { RegisterComponent } from './components/register/register.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { NavComponent } from './components/nav/nav.component';

const routes: Routes = [

   {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'main', component: MainComponent},
   {path: 'welcome', component: NavComponent},
   {path: 'register', component: RegisterComponent},
   {path: 'login', component: LoginComponent},
   {path: '**', component: LoginComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
