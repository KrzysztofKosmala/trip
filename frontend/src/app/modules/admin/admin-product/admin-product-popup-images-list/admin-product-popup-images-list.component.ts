import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-admin-product-popup-images-list',
  templateUrl: './admin-product-popup-images-list.component.html',
  styleUrls: ['./admin-product-popup-images-list.component.scss']
})
export class AdminProductPopupImagesListComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<AdminProductPopupImagesListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  ngOnInit(): void {
  }
  isVisible = false;

  showPopup() {
    this.isVisible = true;
  }

  closePopup() {
    this.isVisible = false;
  }
  onClose(): void {
    this.dialogRef.close();
  }

}
