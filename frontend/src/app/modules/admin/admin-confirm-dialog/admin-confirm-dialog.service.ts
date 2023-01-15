import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AdminConfirmDialogComponent } from './admin-confirm-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class AdminConfirmDialogService {

  openConfirmDialog(message: string): MatDialogRef<AdminConfirmDialogComponent, Boolean>{
      return this.dialog.open(AdminConfirmDialogComponent, {
        width: '30%',
        data: {
          message: message
        }
      });
  }

  constructor(private dialog: MatDialog) { }
}
