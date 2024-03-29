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
import { AdminLoginComponent } from './modules/admin/admin-login/admin-login.component';
import { FullpageadminclearComponent } from './layouts/fullpageadminclear/fullpageadminclear.component';
import { AdminAuthorizeGuard } from './modules/admin/common/guard/AdminAuthorizeGuard';
import { ProfileComponent } from './modules/profile/profile.component';
import { ResetPasswordComponent } from './modules/reset-password/reset-password.component';
import { ConfirmAccountComponent } from './modules/confirm-account/confirm-account.component';
import { UpdateProfileComponent } from './modules/profile/update-profile/update-profile.component';
import { AdminHomeComponent } from './modules/admin/admin-home/admin-home.component';
import { UpdateOrderComponent } from './modules/order/update-order/update-order.component';

const routes: Routes = [
  {
    path:'', component: FullpageComponent, children: [
      {path: '', component: HomeComponent},
      {path: 'login', component: LoginComponent},
      {path: 'products/:slug', component: ProductDetalisComponent},
      {path: 'order/:slug', component: OrderComponent},
      {path: 'profile', component: ProfileComponent},
      {path: 'resetPassword', component: ResetPasswordComponent},
      { path: 'resetPassword/:hash', component: ResetPasswordComponent },
      { path: 'confirmAccount/:hash', component: ConfirmAccountComponent },
      { path: 'profile/update', component: UpdateProfileComponent },
      { path: 'orders/update/:id', component: UpdateOrderComponent }
    ]
  },
  {
    path:'', component: DefaultComponent, children: [
      {path: 'products', component: ProductComponent},
     
    ]
  },

  {
    path:'', component: FullpageadminComponent, children: [
      {path: 'admin', component: AdminComponent , canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/products', component: AdminProductComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/products/update/:id', component: AdminProductUpdateComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/products/add', component: AdminProductAddComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/images', component: AdminImageComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/images/add', component: AdminImageAddComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/orders', component: AdminOrderComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/orders/update/:id', component: AdminOrderUpdateComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/orders/export', component: AdminOrderExportComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/orders/stats', component: AdminOrderStatsComponent, canActivate: [AdminAuthorizeGuard]},
      {path: 'admin/home', component: AdminHomeComponent, canActivate: [AdminAuthorizeGuard]}
    ]
  },

  {
    path:'', component: FullpageadminclearComponent, children: [

      {path: 'admin/login', component: AdminLoginComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
