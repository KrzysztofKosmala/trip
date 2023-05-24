import { Component, OnInit } from '@angular/core';
import { ConfirmAccountService } from './confirm-account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-confirm-account',
  templateUrl: './confirm-account.component.html',
  styleUrls: ['./confirm-account.component.scss']
})
export class ConfirmAccountComponent implements OnInit{

  constructor(    private snackBar: MatSnackBar, private route: ActivatedRoute, private service: ConfirmAccountService, private router: Router){}

  ngOnInit(): void {
    this.service.confirmAccount({
      hash: this.route.snapshot.params['hash']
      }).subscribe({
        next: () => {
          this.router.navigate(["/login"]).then(() => this.snackBar.open("Konto zostało aktywowane","", {duration: 3000}))
        }
        });
        
  }

}


