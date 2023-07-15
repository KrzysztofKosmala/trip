import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile.service';
import { UserDetails } from '../model/userDetails';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.scss']
})
export class UpdateProfileComponent implements OnInit  {

  constructor(private service: ProfileService, private formBuilder: FormBuilder,private snackBar: MatSnackBar,){}
  detailsForm!: FormGroup;
  ngOnInit(): void {
    this.getProfile();
    this.detailsForm = this.formBuilder.group({
      firstname: [],
      lastname: [],
      email: []
    })
  }

  getProfile()
  {
    this.service.getDetails().subscribe(details => this.mapFormValues(details))
  }

  mapFormValues(details: UserDetails): void {
    this.detailsForm.setValue(
      {
        firstname: details.firstname,
        lastname: details.lastname,
        email: details.email
      }
    )
  }

  submit()
  {
    console.log(this.detailsForm)
    this.service.updateDetails
    (
      {
        firstname: this.detailsForm.get('firstname')?.value,
        lastname: this.detailsForm.get('lastname')?.value,
        email: this.detailsForm.get('email')?.value,
      } as UserDetails
    ).subscribe
    (
      {
        next: details =>
        {
          this.mapFormValues(details)
          this.snackBar.open("Twoje dane zostały zapisane", '', {duration: 3000})
        }
      }
    )
  }
}
