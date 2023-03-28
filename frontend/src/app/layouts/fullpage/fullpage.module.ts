import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FullpageComponent } from './fullpage.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from 'src/app/shared/shared.module';
import { LoginComponent } from 'src/app/modules/login/login.component';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { OrderComponent } from 'src/app/modules/order/order.component';
import { ReactiveFormsModule } from '@angular/forms'; 
@NgModule({
  declarations: [
    FullpageComponent,
    LoginComponent,
    HomeComponent,
    OrderComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class FullpageModule { }
