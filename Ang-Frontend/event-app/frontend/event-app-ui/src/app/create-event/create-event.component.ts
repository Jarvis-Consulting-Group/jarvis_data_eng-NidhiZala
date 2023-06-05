import { Component } from '@angular/core';
import { EventServiceService } from '../event-service.service';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent {
  formData : any = {}; // initialise for data
  constructor (private eventService : EventServiceService){}
  createEvent(){
    this.eventService.createEvent(this.formData).subscribe((response)=> {
      console.log("Event Created successfully");
      this.formData ={};
    });
  }


}
