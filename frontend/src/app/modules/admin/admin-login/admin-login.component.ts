import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminLoginService } from './admin-login.service';
import { JwtService } from '../../common/service/jwt.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit{
  loginError=false;

  ngOnInit(): void {
    this.formGroup= this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  constructor(
    private formBuilder: FormBuilder, 
    private adminLoginService: AdminLoginService, 
    private jwtService: JwtService,
    private router: Router
    ){}


  formGroup!: FormGroup;

  submit()
  {
    if(this.formGroup.valid){
      this.adminLoginService.login(this.formGroup.value).subscribe({
        next: (response) => {
          this.loginError = false;
          this.jwtService.setToken(response.access_token);
          this.router.navigate(["/admin"])
        },
        error: () => this.loginError = true
      })
    }
  }
}
