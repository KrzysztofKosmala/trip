import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { PasswordService } from './password.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit{

  constructor(    private formBuilder: FormBuilder,
    private resetService: PasswordService,
    private snackBar: MatSnackBar, 
    private route: ActivatedRoute,
    private router: Router){}
    formGroupChangePassword!: FormGroup;
    formChangePasswordError = "";
    resetForm!: FormGroup;
    resetError = false;
    resetErrorMessage!: String;
    hash = "";
    ngOnInit(): void {
      this.resetForm = this.formBuilder.group({
        email: ['', Validators.required]
      })
      this.hash = this.route.snapshot.params['hash'];
      this.formGroupChangePassword = this.formBuilder.group({
        password: ['', Validators.required],
        repeatPassword: ['', Validators.required]
        });
        
    }
    sendChangePassword(){
      if (this.formGroupChangePassword.valid &&
      this.passwordIdentical(this.formGroupChangePassword.value)) {
        this.resetService.changePassword({
          password:
          this.formGroupChangePassword.get("password")?.value,
          repeatPassword:
          this.formGroupChangePassword.get("repeatPassword")?.value,
          hash: this.hash
          }).subscribe({
            next: () => {
            this.formChangePasswordError = ""
            this.formGroupChangePassword.reset();
            this.snackBar.open('Hasło zostało zmienione', '', {
            duration: 3000, panelClass: "snack-bar-bg-color-ok" });
            },
            error: error => this.formChangePasswordError =
            error.error.message
            });
            
          
      }
      }

      private passwordIdentical(changePassword: any) {
        if (changePassword.password === changePassword.repeatPassword) {
        this.formChangePasswordError = "";
        return true;
        }
        this.formChangePasswordError = "Hasła nie są identyczne";
        return false;
        }
        
    resetPassword()
    {
        this.resetService.resetPassword({ email: this.resetForm.get("email")?.value}).subscribe({
          next: result => {
          this.resetErrorMessage = "";
          this.resetForm.reset();
          this.snackBar.open('Email z linkiem został wysłany', '', {
          duration: 3000, panelClass: "snack-bar-bg-color-ok" });
          },
          error: error => this.resetErrorMessage = error.error.message
          });
    }


    
}
