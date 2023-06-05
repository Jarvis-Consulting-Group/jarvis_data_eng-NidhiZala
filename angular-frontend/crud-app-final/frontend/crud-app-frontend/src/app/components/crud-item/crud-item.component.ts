import { Component, OnInit } from '@angular/core';
import { CrudItem } from 'src/app/models/crud-item.model';
import { CrudServiceService } from 'src/app/services/crud-service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-crud-item',
  templateUrl: './crud-item.component.html',
  styleUrls: ['./crud-item.component.css']
})
export class CrudItemComponent {
  item: CrudItem = {
    id: 0,
    name: '',
    description: ''
  };

  constructor(private crudService: CrudServiceService, private http: HttpClient) { }

  createItem(): void {
    this.crudService.createCrudItem(this.item).subscribe(
      () => {
        console.log("Item created successfully");
        this.item = { id: 0, name: '', description: '' };
      },
      error => {
        console.log("Error in creating the item");
      }
    );
  }
}
