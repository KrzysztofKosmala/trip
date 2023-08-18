import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DefaultComponent } from './default.component';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { ProductComponent } from 'src/app/modules/product/product.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductDetalisComponent } from 'src/app/modules/product-detalis/product-detalis.component';
import { ProfileComponent } from 'src/app/modules/profile/profile.component';
import { UpdateProfileComponent } from 'src/app/modules/profile/update-profile/update-profile.component';
import { UpdateOrderComponent } from 'src/app/modules/order/update-order/update-order.component';

@NgModule({
  declarations: [
    DefaultComponent,
    ProductComponent,
    ProductDetalisComponent,
    ProfileComponent,
    UpdateProfileComponent,
    UpdateOrderComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    SharedModule,
    ReactiveFormsModule
]})
export class DefaultModule { }
