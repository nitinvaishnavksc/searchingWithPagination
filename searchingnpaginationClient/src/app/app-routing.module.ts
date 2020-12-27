import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchListComponent } from './search-list/search-list.component';
import { SearchingComponent } from './searching/searching.component';

const routes: Routes = [  
  { path: '', redirectTo: 'search', pathMatch: 'full' },    
  { path: 'search', component: SearchingComponent },    
  { path: 'search-list', component: SearchListComponent },    
  //{ path: 'contact', component: ContactComponent },   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
