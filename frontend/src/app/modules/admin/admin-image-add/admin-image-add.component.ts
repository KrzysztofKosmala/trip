import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminImageService } from '../admin-image/admin-image-service';
import { UploadResponse } from '../admin-image/model/uploadResponse';
@Component({
  selector: 'app-admin-image-add',
  templateUrl: './admin-image-add.component.html',
  styleUrls: ['./admin-image-add.component.scss']
})
export class AdminImageAddComponent implements OnInit {

  imageForm!: FormGroup;
  requiredFileTypes ="image/jpeg, image/png";
  selectedFile!: File;
  response!: UploadResponse;

  destinations = [
    { value: 'PL', viewValue: 'Polska' },
    { value: 'AU', viewValue: 'Austria' },
    { value: 'FR', viewValue: 'Francja' }
  ];

  constructor(private formBuilder: FormBuilder, private adminImageService: AdminImageService, private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.imageForm  = this.formBuilder.group({
      file: [''],
      country: ['', Validators.required],
      description: ['', Validators.maxLength(255)]
    })
  }
  onFileChange(event: any){
    if(event.target.files.length>0)
      this.selectedFile = event.target.files[0];
  }
  uploadFile(){
    const formData = new FormData();
    formData.append('image', this.selectedFile);
    formData.append('country', this.imageForm.get('country')!.value);
    formData.append('description', this.imageForm.get('description')!.value);

    this.adminImageService.uploadFile(formData).subscribe(response => {
      this.response = response;
      this.router.navigate(['admin/images']).then(()=> this.snackBar.open("Obraz został dodany " + response.filename,"", {duration: 3000}));
    }, error => {
      console.log(error);
      this.snackBar.open("Obraz nie został dodany ","", {duration: 3000})
    });
  }

}
