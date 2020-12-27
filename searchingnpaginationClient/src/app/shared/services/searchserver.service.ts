import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchserverService {
  
  apiUrl: string = 'http://localhost:9000/search/';

  searchedData:string;

  constructor(private httpClient: HttpClient) { }

  searchList(searchkey:string): Observable<any> 
  {
    return this.httpClient.get(`${this.apiUrl}searchbykey?skeyword=${searchkey}`);
  }

  searchResultList(searchkey:string,page:string,size:string): Observable<any> 
  {
    return this.httpClient.get(`${this.apiUrl}searchbyenter?skeyword=${searchkey}&page=${page}&size=${size}`);
  }
}
