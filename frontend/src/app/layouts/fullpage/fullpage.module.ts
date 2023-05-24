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
import { ReplacePipe } from 'src/app/modules/common/pipe/replacePipe';
import { ResetPasswordComponent } from 'src/app/modules/reset-password/reset-password.component';
import { ConfirmAccountComponent } from 'src/app/modules/confirm-account/confirm-account.component';
@NgModule({
  declarations: [
    FullpageComponent,
    LoginComponent,
    HomeComponent,
    OrderComponent,
    ResetPasswordComponent,
    ReplacePipe,
    ConfirmAccountComponent
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
