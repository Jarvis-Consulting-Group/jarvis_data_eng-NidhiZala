import { Component, OnInit } from '@angular/core';
import { Quote } from '../quote';
import { QuotesService } from '../quotes.service';

@Component({
  selector: 'app-quotes-list',
  templateUrl: './quotes-list.component.html',
  styleUrls: ['./quotes-list.component.css']
})
export class QuotesListComponent implements OnInit {
  quotes: Quote[] = [];

  constructor(private quotesService: QuotesService) {}

  ngOnInit(): void {
    this.quotesService.getDataSource().subscribe((data: Quote[]) => {
      this.quotes = data;
    });
  }
}
