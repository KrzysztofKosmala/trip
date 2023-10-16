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
  details!: UserDetails;
  genders = new Map<string, string>([
    ["Mężczyzna", "MALE"],
    ["Kobieta", "FEMALE"]
  ])

  ngOnInit(): void {
    this.getProfile();
    this.detailsForm = this.formBuilder.group({
      firstname: [],
      lastname: [],
      email: [],
      phone: [],
      pesel: [],
      gender: [],
      street: [],
      postal: [],
      city: []
    })
  }

  getProfile()
  {
    this.service.getDetails().subscribe(details => {
      this.mapFormValues(details);
    })
  }

  mapFormValues(details: UserDetails): void {
    this.detailsForm.setValue(
      {
        firstname: details.firstname,
        lastname: details.lastname,
        email: details.email,
        phone: details.phone,
        pesel: details.pesel,
        gender: details.gender,
        street: details.street,
        postal: details.postal,
        city: details.city
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

        phone: this.detailsForm.get('phone')?.value,
        pesel: this.detailsForm.get('pesel')?.value,
        gender: this.genders.get(this.detailsForm.get('gender')?.value),
        street: this.detailsForm.get('street')?.value,
        postal: this.detailsForm.get('postal')?.value,
        city: this.detailsForm.get('city')?.value,
      } as UserDetails
    ).subscribe
    (
      details =>
        {
          this.mapFormValues(details)
          this.snackBar.open("Twoje dane zostały zapisane", '', {duration: 3000})
        }
      , error =>{
        this.snackBar.open(error.error.message, '', {duration: 3000})

      }
    )
  }
}
