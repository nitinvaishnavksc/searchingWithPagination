import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchserverService } from '../shared/services/searchserver.service';

@Component({
  selector: 'app-searching',
  templateUrl: './searching.component.html',
  styleUrls: ['./searching.component.scss']
})
export class SearchingComponent implements OnInit {

  isShown: boolean = false ;
  searchSuggestions:any;
  seatchForm = new FormGroup({
    "searchEnter": new FormControl("", Validators.required)
  });

  constructor(private searchService:SearchserverService,
    public router: Router) 
  {

   }

  ngOnInit(): void {
  }

  getSearchResult(searchdata:any)
  {
    this.searchService.searchedData = searchdata.searchEnter;
    this.router.navigate(['search-list']);
  }

  onKey(searchkeyword)
  {
    let that =this;
    if(searchkeyword!=undefined && searchkeyword.target.value.trim()!="")
    {
      this.searchService.searchList(searchkeyword.target.value.trim()).subscribe(
        data=>
        {
          that.isShown=true;
          that.searchSuggestions=data;
        },
        error=>
        {
          console.log("searching Key Error",error);
        }
      )
    }
    else{
      that.isShown=false;
      that.searchSuggestions=null;
    } 
  }
}
