import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatCardModule,
    MatCheckboxModule, 
    MatSidenavModule,
    MatToolbarModule
  ],
  exports: 
  [
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatCardModule,
    MatCheckboxModule, 
    MatSidenavModule,
    MatToolbarModule
  ]
  
})
export class MaterialModule { }
