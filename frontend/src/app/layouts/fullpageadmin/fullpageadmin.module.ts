import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FullpageadminComponent } from './fullpageadmin.component';
import { AdminComponent } from 'src/app/modules/admin/admin.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from 'src/app/shared/material.module';
import { AdminProductComponent } from 'src/app/modules/admin/admin-product/admin-product.component';
import { AdminProductUpdateComponent } from 'src/app/modules/admin/admin-product/admin-product-update/admin-product-update.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AdminProductAddComponent } from 'src/app/modules/admin/admin-product/admin-product-add/admin-product-add.component';
import { AdminProductFormComponent } from 'src/app/modules/admin/admin-product/admin-product-form/admin-product-form.component';
import { AdminMessagesComponent } from 'src/app/modules/admin/common/component/admin-messages/admin-messages.component';
import { AdminConfirmDialogComponent } from 'src/app/modules/admin/common/component/admin-confirm-dialog/admin-confirm-dialog.component';
import { AdminImageAddComponent } from 'src/app/modules/admin/admin-image-add/admin-image-add.component';
import { AdminImageComponent } from 'src/app/modules/admin/admin-image/admin-image.component';
import { AdminProductPopupImagesListComponent } from 'src/app/modules/admin/admin-product/admin-product-popup-images-list/admin-product-popup-images-list.component';
import { AdminOrderComponent } from 'src/app/modules/admin/admin-order/admin-order.component';
import { AdminOrderUpdateComponent } from 'src/app/modules/admin/admin-order/admin-order-update/admin-order-update.component';
import { AdminOrderExportComponent } from 'src/app/modules/admin/admin-order/admin-order-export/admin-order-export.component';
import { AdminOrderStatsComponent } from 'src/app/modules/admin/admin-order/admin-order-stats/admin-order-stats.component';

@NgModule({
  declarations: [
    FullpageadminComponent,
    AdminComponent,
    AdminProductComponent,
    AdminProductUpdateComponent,
    AdminProductAddComponent,
    AdminProductFormComponent,
    AdminMessagesComponent,
    AdminConfirmDialogComponent,
    AdminImageComponent,
    AdminImageAddComponent,
    AdminProductPopupImagesListComponent,
    AdminOrderComponent,
    AdminOrderUpdateComponent,
    AdminOrderExportComponent,
    AdminOrderStatsComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    MaterialModule,
    ReactiveFormsModule
  ]
})
export class FullpageadminModule { }
