import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {PaginationComponent} from "../shared/pagination/pagination.component";
import {PaginationPipe, PaginationInterface} from "../shared/pagination/pagination.pipe";
import { SearchserverService } from '../shared/services/searchserver.service';

@Component({
  selector: 'app-search-list',
  templateUrl: './search-list.component.html',
  styleUrls: ['./search-list.component.scss']
})
export class SearchListComponent implements OnInit {

  searchResults:any;
  currentPage: number = 1;
  totalItems: number = 200; 
  pageSize: number = 7; 
  toRecord: number =1;
  uptoRecord: number =1;
  
  seatchlistForm = new FormGroup({
    "searchBar": new FormControl("", Validators.required)
  });

  constructor(private searchService:SearchserverService) { }

  ngOnInit(): void {
    this.seatchlistForm.controls.searchBar.setValue(this.getSearchedData());
    this.reloadData();
  }

  reloadData()
  {
  
    this.seatchlistForm.get('searchBar').value
    let that =this;
    if(this.seatchlistForm.get('searchBar').value!=undefined && this.seatchlistForm.get('searchBar').value.trim()!="")
    {
      this.searchService.searchResultList(this.seatchlistForm.get('searchBar').value.trim(),String(this.currentPage-1),String(this.pageSize)).subscribe(
        data=>
        {
          that.searchResults=data;
          let dataserver= data;
          that.searchResults = dataserver.datatable;
          this.totalItems = dataserver.numberofrecords;
          this.toRecord = dataserver.offset;
          this.uptoRecord = dataserver.onset;
          
        },
        error=>
        {
          console.log("searching Key Error",error);
        }
      )
    }
    
  }

  getSearchedData():string
  {
    debugger;
    return this.searchService.searchedData;
  }

  public onPageChange(event: any): void {
      this.currentPage = event.currentPage;
      this.reloadData();
  };
  
  public onPageSizeChange(event: any): void
  {
    this.pageSize = event.pageSize;
    this.currentPage = 1;
    this.reloadData();
  }
  
  public paginationArgs() : PaginationInterface{
    return {
          currentPage : this.currentPage,
          totalItems : this.totalItems,
          pageSize : this.pageSize
    }
  }

}
