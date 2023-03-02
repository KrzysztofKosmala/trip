import { AfterViewInit, Component, EventEmitter, Inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { startWith, switchMap } from 'rxjs';
import { Page } from 'src/app/shared/model/page';
import { AdminImageService } from '../../admin-image/admin-image-service';
import { Image } from '../../admin-image/model/Image';

@Component({
  selector: 'app-admin-product-popup-images-list',
  templateUrl: './admin-product-popup-images-list.component.html',
  styleUrls: ['./admin-product-popup-images-list.component.scss']
})
export class AdminProductPopupImagesListComponent implements  AfterViewInit {

  @Input() destination!: string;
  @Output() selectedImages: EventEmitter<Image[]> = new EventEmitter();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  totalElements = 0;
  dataSource: Image[] = [];
  selected: Image[] = [];

  constructor(
    private imageService: AdminImageService,
    private dialogRef: MatDialogRef<AdminProductPopupImagesListComponent>
  ) {
    
  }

  displayedColumns: string[] = ['select', 'data', 'name', 'desc', 'destination'];

  ngAfterViewInit(): void {
    this.paginator.page
      .pipe(
        startWith({}),
        switchMap(() => {
          return this.imageService.getImagesByCountry(
            this.paginator.pageIndex,
            this.paginator.pageSize,
            this.destination
          );
        })
      )
      .subscribe((data) => {
        this.totalElements = data.totalElements;
        this.dataSource = data.content;
        // ustawiamy zaznaczenie dla obrazów, które znajdują się w selectedImages
        this.dataSource.forEach((selectedImage) => {
          const image = this.selected.find((image) => image.id === selectedImage.id);
          if (image) {
            this.selected.push(image);
          }
        });
      });
  }

  toggleCheckbox(image: Image): void {
    if (this.isSelected(image)) {
      this.selected = this.selected.filter((selectedImage: Image) => selectedImage !== image);
    } else {
      this.selected.push(image);
    }
  }

  isSelected(image: Image): boolean {

    console.log("image " + image)
    console.log("selected list: " + this.selected)
    console.log("is image on selected list? " + this.selected.includes(image))
    return this.selected.includes(image);
  }

  masterToggle(): void {
    if (this.isAllSelected()) {
      this.selected = [];
    } else {
      this.selected = [...this.dataSource];
    }
  }

  isAllSelected(): boolean {
    return this.selected.length === this.dataSource.length;
  }

  onAddClick() {
    console.log(this.selected);
    this.selectedImages.emit(this.selected);
    this.dialogRef.close();
  }

}