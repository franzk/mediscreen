import { AppRoutingModule } from './app-routing.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { HttpClientModule } from '@angular/common/http';
import { PatientComponent } from './components/patient/patient.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientListComponent,
    PatientComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
