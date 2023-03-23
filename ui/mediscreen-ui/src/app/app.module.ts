import { AppRoutingModule } from './app-routing.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { HttpClientModule } from '@angular/common/http';
import { PatientDisplayComponent } from './components/patient-display/patient-display.component';
import { HeaderComponent } from './components/header/header.component';
import { PatientFormComponent } from './components/patient-form/patient-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NoteFormComponent } from './components/note-form/note-form.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientListComponent,
    PatientDisplayComponent,
    HeaderComponent,
    PatientFormComponent,
    NoteFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
