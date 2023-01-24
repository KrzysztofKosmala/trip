import { Component, AfterViewInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable } from '@angular/material/table';
import { startWith, switchMap } from 'rxjs';
import { AdminConfirmDialogService } from '../admin-confirm-dialog/admin-confirm-dialog.service';
import { AdminProductService } from '../admin-product/admin-product.service';
import { AdminProduct } from '../admin-product/adminProduct';
import { AdminImageService } from './admin-image-service';
import { UploadResponse } from './model/uploadResponse';

@Component({
  selector: 'app-admin-image',
  templateUrl: './admin-image.component.html',
  styleUrls: ['./admin-image.component.scss']
})
export class AdminImageComponent implements AfterViewInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatTable) table!: MatTable<any>;
  displayedColumns: string[] = ["id", "image", "path"];
  totalElements: number = 0;
  dataSource: UploadResponse[] = [];


  constructor(private adminImageService: AdminImageService, private dialogService: AdminConfirmDialogService) { }

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


}
