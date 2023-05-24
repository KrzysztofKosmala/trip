import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './login.service';
import { JwtService } from '../common/service/jwt.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
     private loginService: LoginService,
     private jwtService: JwtService,
     private snackBar: MatSnackBar, 
     private router: Router
     ) { }

     private readonly REDIRECT_ROUTE = "/profile";
  loginForm!: FormGroup;
  loginError =false;
  registerErrorMessage!: String;
  registerForm!: FormGroup;
  registerError =false;
  ngOnInit(): void {

    console.log(this.jwtService.isLoggedIn())
    if(this.jwtService.isLoggedIn())
    {
      this.router.navigate([this.REDIRECT_ROUTE]);
    }


    this.loginForm= this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })

    this.registerForm= this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required]
    })
  }

  login()
  {
    if(this.loginForm.valid && this.isPasswordIdetical(this.registerForm.value))
    {
      this.loginService.login(this.loginForm.value).subscribe({
        next: response => {
          this.jwtService.setToken(response.access_token)
          
          this.jwtService.setAdminAccess(false);
          this.router.navigate([this.REDIRECT_ROUTE]).then(() => this.snackBar.open("Zalogowano pomyślnie","", {duration: 3000}))
          this.loginError = false;
        },
        error: err => {
          this.loginError = true;
          if(err.error.message)
          {
            this.registerErrorMessage = err.error.message;
          }else{
            this.registerErrorMessage = "Coś poszło nie tak."
          }
        }
      });
    }
  }
  register()
  {
    if(this.registerForm.valid && this.isPasswordIdetical(this.registerForm.value))
      {
        this.loginService.register(this.registerForm.value).subscribe({
          next: response => {
            
            this.jwtService.setAdminAccess(false);
            this.router.navigate(["/"]).then(() => this.snackBar.open("Konto zostało zarejestrowane","", {duration: 3000}))
          },
          error: err => {
            this.registerError = true;
            if(err.error.message)
            {
              this.registerErrorMessage = err.error.message;
            }else{
              this.registerErrorMessage = "Coś poszło nie tak."
            }
          }
        });
      }
  }

  private isPasswordIdetical(register: any): boolean
  {
      if(register.password === register.repeatPassword)
      {
this.registerError = false;
return true;
      }
 this.registerError = true;
 this.registerErrorMessage = "Hasła nie są identyczne";
 return false;
    }
}
