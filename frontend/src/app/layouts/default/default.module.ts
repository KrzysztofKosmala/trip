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

@NgModule({
  declarations: [
    DefaultComponent,
    HomeComponent,
    ProductComponent,
    ProductDetalisComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    SharedModule,
    ReactiveFormsModule
]})
export class DefaultModule { }
