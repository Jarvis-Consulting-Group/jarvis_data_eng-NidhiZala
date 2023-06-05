// import { Component, OnInit } from '@angular/core';
// import { CrudServiceService } from 'src/app/services/crud-service.service';
// import { CrudItem } from 'src/app/models/crud-item.model';

// @Component({
//   selector: 'app-crud-list',
//   templateUrl: './crud-list.component.html',
//   styleUrls: ['./crud-list.component.css']
// })
// export class CrudListComponent implements OnInit {
//   crudItems: CrudItem[] = [];

//   constructor(private crudService: CrudServiceService) {}

//   ngOnInit(): void {
//     this.fetchCrudItems();
//   }

//   fetchCrudItems(): void {
//     this.crudService.getCrudItem().subscribe(
//       items => {
//         this.crudItems = items;
//       },
//       error => {
//         console.error('Error fetching CRUD items:', error);
//       }
//     );
//   }
//   deleteItem(id: number): void {
//     console.log("Number: ",id); 
//     this.crudService.deleteCrudItem(id).subscribe(
//       () => {
//         console.log("Item deleted successfully");
//         this.fetchCrudItems(); // Refresh the item list
//       },
//       error => {
//         console.log("Error deleting the item");
//       }
//     );
//   }
  
// }
import { Component, OnInit } from '@angular/core';
import { CrudServiceService } from 'src/app/services/crud-service.service';
import { CrudItem } from 'src/app/models/crud-item.model';

@Component({
  selector: 'app-crud-list',
  templateUrl: './crud-list.component.html',
  styleUrls: ['./crud-list.component.css']
})
export class CrudListComponent implements OnInit {
  crudItems: CrudItem[] = [];
  selectedItem: CrudItem | null = null;
  editedItem: CrudItem | null = null;

  constructor(private crudService: CrudServiceService) {}

  ngOnInit(): void {
    this.fetchCrudItems();
  }

  fetchCrudItems(): void {
    this.crudService.getCrudItem().subscribe(
      items => {
        this.crudItems = items;
      },
      error => {
        console.error('Error fetching CRUD items:', error);
      }
    );
  }

  deleteItem(id: number): void {
    console.log("Number: ", id);
    this.crudService.deleteCrudItem(id).subscribe(
      () => {
        console.log("Item deleted successfully");
        this.fetchCrudItems(); // Refresh the item list
      },
      error => {
        console.log("Error deleting the item");
      }
    );
  }
  editItem(item: CrudItem): void {
    this.selectedItem = item;
    this.editedItem = { ...item }; // Create a copy of the item for editing
  }

  cancelEdit(): void {
    this.selectedItem = null;
    this.editedItem = null;
  }

  updateItem(): void {
    if (this.selectedItem) {
      const updatedItem: CrudItem = {
        id: this.selectedItem.id,
        name: this.selectedItem.name,
        description: this.selectedItem.description
      };
  
      this.crudService.updateCrudItem(updatedItem).subscribe(
        () => {
          console.log("Item updated successfully");
          this.selectedItem = null;
          this.fetchCrudItems(); // Refresh the item list
        },
        error => {
          console.log("Error updating the item");
        }
      );
    }
  }
}