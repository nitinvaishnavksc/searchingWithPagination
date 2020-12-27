import {Component, Input, Output, EventEmitter, OnInit, OnChanges} from "@angular/core";

@Component({
  selector: 'ng-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnChanges {
  @Input("previous-text") previousText: string;
  @Input("next-text") nextText: string;
  @Input("first-text") firstText: string;
  @Input("last-text") lastText: string;
  @Input("totalItems") totalItems: number;
  @Input("currentPage") currentPage: number;
  @Input("pageSize") pageSize: number;
  @Input("offset") offset: number = 3;

  @Input("toRecord") toRecord: number;
  @Input("uptoRecord") uptoRecord: number;

  @Output("pageChange") pageChange = new EventEmitter();
  @Output("pageSizeChange") pageSizeChange = new EventEmitter();

  selectedPageNo: number;
  pageList: Array<number> = [];
  totalSize: number;
  nextItemValid: boolean;
  previousItemValid: boolean;

  /**
   * On changes
   * @param changes
   */
  ngOnChanges(changes: any): void {
      this.selectedPageNo = this.currentPage;

      this.doPaging();
  }

  /**
   * Do paging
   */
  doPaging() {
      this.pageList = [];
      this.selectedPageNo = this.selectedPageNo || 1;

      let i: number = 1, count: number = 0;
      let remaining = this.totalItems % this.pageSize;
      this.totalSize = Math.round(((this.totalItems - remaining) / this.pageSize) + (remaining === 0 ? 0 : 1));

      if (this.selectedPageNo > this.totalSize - this.offset) {
          i = this.totalSize - this.offset * 2;
          i = i <= 0 ? 1 : i;
      } else if (this.selectedPageNo > this.offset) {
          i = this.selectedPageNo - this.offset;
      }

      for (count = 0; i <= this.totalSize && count < 7; i++, count++) {
          this.pageList.push(i);
      }

      //next validation
      if (i - 1 < this.totalSize) {
          this.nextItemValid = true;
      } else {
          this.nextItemValid = false;
      }
      //previous validation
      if ((this.selectedPageNo) > 1) {
          this.previousItemValid = true;
      } else {
          this.previousItemValid = false;
      }
  }

  /**
   * Set current page
   * @param pageNo
   */
  setCurrentPage(pageNo: number) {
      this.selectedPageNo = pageNo;
      this.pageChageListner();
      this.doPaging()
  }

  /**
   * Go to first page
   */
  firstPage() {
      this.selectedPageNo = 1;
      this.pageChageListner();
      this.doPaging()
  }

  /**
   * Go to last page
   */
  lastPage() {
      let totalPages = (this.totalItems / this.pageSize);
      let lastPage = (totalPages) - (totalPages % this.pageSize === 0 ? this.pageSize : totalPages % this.pageSize) + 1;
      this.selectedPageNo = this.totalSize;
      this.pageChageListner();
      this.doPaging()
  }

  /**
   * Go to next page
   */
  nextPage() {
      this.selectedPageNo = this.selectedPageNo + 1; //pageNo;
      this.pageChageListner();
      this.doPaging()
  }

  /**
   * Go to previous page
   */
  previousPage() {
      this.selectedPageNo = this.selectedPageNo - 1; //page > 0 ? page : 1;
      this.pageChageListner();
      this.doPaging();
  }

  /**
   * Page listener
   */
  pageChageListner() {
      this.pageChange.emit({
          currentPage: this.selectedPageNo
      })
  }

  /**
   * Go Paging 
   */
  goPage(event){
      let pagenumber;
      if(event.target.classList.value =="fa fa-arrow-circle-right"){
          pagenumber = event.target.parentElement.nextElementSibling.value.trim();
      }
      else{
          pagenumber = event.target.nextSibling.value.trim();
      }
      if(pagenumber!="" && this.totalSize>=Number(pagenumber) && Number(pagenumber)>0)
      {
          this.selectedPageNo = Number(pagenumber);
          this.pageChageListner();
          this.doPaging();
      }
      else
      {
          alert('Oops...Page Not Found! error');
      }
  }

      /**
   * Change Page Size
   */
  changePageSize(event)
  {
      let changepagesize;
      if(event.target.classList.value=="fa fa-sort-numeric-asc")
      {
          changepagesize = event.target.parentElement.nextElementSibling.value.trim();
      }
      else
      {
          changepagesize = event.target.nextSibling.value.trim();
      }

      if(changepagesize!="" && Number(changepagesize)>0)
      {
          this.pageSize = Number(changepagesize);
          this.pageSizeChageListner();
          this.doPaging();
      }


  }

      /**
   * Page listener
   */
  pageSizeChageListner() {
      this.pageSizeChange.emit({
          pageSize: this.pageSize
      })
  }


}
