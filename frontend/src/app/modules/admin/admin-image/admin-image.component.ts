import { Component, AfterViewInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable } from '@angular/material/table';
import { startWith, switchMap } from 'rxjs';
import { AdminConfirmDialogService } from '../common/service/admin-confirm-dialog.service';
import { AdminProductService } from '../admin-product/admin-product.service';
import { AdminProduct } from '../admin-product/adminProduct';
import { AdminImageService } from './admin-image-service';
import { Image } from './model/Image';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-image',
  templateUrl: './admin-image.component.html',
  styleUrls: ['./admin-image.component.scss']
})
export class AdminImageComponent implements AfterViewInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatTable) table!: MatTable<any>;
  displayedColumns: string[] = [ "name", "desc", "destination", "actions"];
  totalElements: number = 0;
  dataSource: Image[] = [];


  constructor(private sanitizer: DomSanitizer, private adminImageService: AdminImageService, private dialogService: AdminConfirmDialogService) { }

  ngAfterViewInit(): void {
    this.paginator.page.pipe(
      startWith({}),
      switchMap(() => {
        return this.adminImageService.getImages(this.paginator.pageIndex, this.paginator.pageSize);
      })
    ).subscribe(data => {
      this.totalElements = data.totalElements;
      this.dataSource=data.content;
    });
  }

  
  confirmDelete(element: Image){
    this.dialogService.openConfirmDialog("Czy na pewno chcesz usunąć ten produkt?")
    .afterClosed()
    .subscribe(result => 
      {        
        if(result){
          this.adminImageService.delete(element.id)
          .subscribe(() => {
              this.dataSource.forEach((value, index) => {
                  if(element == value){
                    this.dataSource.splice(index, 1);
                    this.table.renderRows();
                  }
              })
          });
        }
      });
  }
  

}
