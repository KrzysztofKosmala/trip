import { Component, OnDestroy, OnInit } from '@angular/core';
import { AdminMessagesService } from './admin-messages.service';

@Component({
  selector: 'app-admin-messages',
  templateUrl: './admin-messages.component.html',
  styleUrls: ['./admin-messages.component.scss']
})
export class AdminMessagesComponent implements OnInit, OnDestroy {

  messages: Array<String> = [];
  private clickCounter: number = 0;

  constructor(private adminMessageService: AdminMessagesService) { }
  ngOnDestroy(): void {
    this.adminMessageService.subject.unsubscribe();
  }

  ngOnInit(): void {
    this.adminMessageService.subject.subscribe(messages => {
      this.messages = messages
      this.clickCounter++;
      setTimeout(() => {
        if(this.clickCounter==1)
        {
          this.clearMessages
        }
        this.clickCounter--;
      }, 5000)
    });
  }

  clearMessages(){
    this.messages = [];
    this.adminMessageService.clear();
  }

}
