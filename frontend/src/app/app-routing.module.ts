import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DefaultComponent } from './layouts/default/default.component';
import { FullpageComponent } from './layouts/fullpage/fullpage.component';
import { FullpageadminComponent } from './layouts/fullpageadmin/fullpageadmin.component';
import { AdminImageAddComponent } from './modules/admin/admin-image-add/admin-image-add.component';
import { AdminImageComponent } from './modules/admin/admin-image/admin-image.component';
import { AdminOrderUpdateComponent } from './modules/admin/admin-order/admin-order-update/admin-order-update.component';
import { AdminOrderComponent } from './modules/admin/admin-order/admin-order.component';
import { AdminProductAddComponent } from './modules/admin/admin-product/admin-product-add/admin-product-add.component';
import { AdminProductUpdateComponent } from './modules/admin/admin-product/admin-product-update/admin-product-update.component';
import { AdminProductComponent } from './modules/admin/admin-product/admin-product.component';
import { AdminComponent } from './modules/admin/admin.component';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/login/login.component';
import { OrderComponent } from './modules/order/order.component';
import { ProductDetalisComponent } from './modules/product-detalis/product-detalis.component';
import { ProductComponent } from './modules/product/product.component';
import { AdminOrderExportComponent } from './modules/admin/admin-order/admin-order-export/admin-order-export.component';
import { AdminOrderStatsComponent } from './modules/admin/admin-order/admin-order-stats/admin-order-stats.component';

const routes: Routes = [
  {
    path:'', component: FullpageComponent, children: [
      {path: '', component: HomeComponent},
      {path: 'login', component: LoginComponent},
      {path: 'products/:slug', component: ProductDetalisComponent},
      {path: 'order/:slug', component: OrderComponent}
    ]
  },
  {
    path:'', component: DefaultComponent, children: [
      {path: 'products', component: ProductComponent}
    ]
  },

  {
    path:'', component: FullpageadminComponent, children: [
      {path: 'admin', component: AdminComponent},
      {path: 'admin/products', component: AdminProductComponent},
      {path: 'admin/products/update/:id', component: AdminProductUpdateComponent},
      {path: 'admin/products/add', component: AdminProductAddComponent},
      {path: 'admin/images', component: AdminImageComponent},
      {path: 'admin/images/add', component: AdminImageAddComponent},
      {path: 'admin/orders', component: AdminOrderComponent},
      {path: 'admin/orders/update/:id', component: AdminOrderUpdateComponent},
      {path: 'admin/orders/export', component: AdminOrderExportComponent},
      {path: 'admin/orders/stats', component: AdminOrderStatsComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
