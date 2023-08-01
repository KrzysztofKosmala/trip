import { Component, OnInit } from '@angular/core';
import { HomePageSettings } from './model/homePageSettings';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminHomeService } from './admin-home.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit 
{
  homePageSettings!: HomePageSettings;
  formGroup!: FormGroup;
  productStrategies!: Map<string,string>;
  constructor(
    private activatedRoute: ActivatedRoute,
    private adminHomeService: AdminHomeService,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {
    this.getSettings();
    this.getInitData();
    this.formGroup = this.formBuilder.group({
      productStrategy: ['', Validators.required]
    })
  }

  getInitData()
  {
    this.adminHomeService.getInitData()
    .subscribe(data => this.productStrategies = data.productStrategies)
  }

  getSettings()
  {
    this.adminHomeService.getSettings()
      .subscribe(settings => {
        console.log(settings)
        this.homePageSettings = settings
        this.formGroup.setValue({
          productStrategy: settings.productStrategy
        }) 
      });
  }

  changeSettings()
  {

  }
}
