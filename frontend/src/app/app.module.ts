import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DefaultComponent } from './layouts/default/default.component';
import { DefaultModule } from './layouts/default/default.module';
import { HomeComponent } from './modules/home/home.component';
import { ProductComponent } from './modules/product/product.component';
import { HeaderComponent } from './shared/components/header/header.component';
import { FullpageComponent } from './layouts/fullpage/fullpage.component';
import { FullpageModule } from './layouts/fullpage/fullpage.module';
import { LoginComponent } from './modules/login/login.component';
import { AdminComponent } from './modules/admin/admin.component';
import { FullpageadminModule } from './layouts/fullpageadmin/fullpageadmin.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DefaultModule,
    FullpageModule,
    FullpageadminModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
