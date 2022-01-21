import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { FormBuilder } from '@angular/forms';
import { Product } from '../store/products/products.reducer';
import { searchProductsByTerm } from './../store/products/products.actions';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {

  searchForm = this.formBuilder.group({
    searchTerm: [''],
});

  constructor(private store: Store<Product>, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  getSearchTerm = () => this.searchForm.get('searchTerm')?.value || "";

  searchProductsByTerm(searchTerm : string) {
    this.store.dispatch(searchProductsByTerm({searchTerm: searchTerm}))
  }




}
