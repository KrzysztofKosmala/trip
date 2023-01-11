import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
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
    MatToolbarModule,
    MatPaginatorModule,
    MatTableModule
  ],
  exports: 
  [
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatCardModule,
    MatCheckboxModule, 
    MatSidenavModule,
    MatToolbarModule,
    MatPaginatorModule,
    MatTableModule
  ]
  
})
export class MaterialModule { }
