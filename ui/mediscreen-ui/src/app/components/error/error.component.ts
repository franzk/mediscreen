import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  statusCode?: string;
  statusText?: string;

  constructor() { }

  ngOnInit(): void {

    this.statusCode = history.state.statusCode;
    this.statusText = history.state.statusText;

  }

}
