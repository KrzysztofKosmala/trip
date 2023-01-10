import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fullpageadmin',
  templateUrl: './fullpageadmin.component.html',
  styleUrls: ['./fullpageadmin.component.scss']
})
export class FullpageadminComponent implements OnInit {

  categories = ["kat1","kat2"]

  constructor() { }

  ngOnInit(): void {
  }

}
