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
import {MatInputModule} from '@angular/material/input';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { NgImageSliderModule } from 'ng-image-slider';
import {MatDatepickerModule } from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
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
    MatTableModule,
    MatInputModule,
    MatSnackBarModule,
    MatDialogModule,
    MatSelectModule,
    MatDialogModule,
    MatSliderModule,
    CarouselModule,
    NgImageSliderModule,
    MatDatepickerModule,
    MatNativeDateModule
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
    MatTableModule,
    MatInputModule,
    MatSnackBarModule,
    MatDialogModule,
    MatSelectModule,
    MatDialogModule,
    MatSliderModule,
    CarouselModule,
    NgImageSliderModule,
    MatDatepickerModule,
    MatNativeDateModule
  ]
  
})
export class MaterialModule { }
