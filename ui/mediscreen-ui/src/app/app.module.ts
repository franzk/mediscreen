import { AuthGard } from './guards/auth-gard';
import { AppRoutingModule } from './app-routing.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PatientDisplayComponent } from './components/patient-display/patient-display.component';
import { HeaderComponent } from './components/header/header.component';
import { PatientFormComponent } from './components/patient-form/patient-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NoteFormComponent } from './components/note-form/note-form.component';
import { AuthInterceptor } from './interceptor/auth-interceptor';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ErrorInterceptor } from './interceptor/error-interceptor';

@NgModule({
  declarations: [
    AppComponent,
    PatientListComponent,
    PatientDisplayComponent,
    HeaderComponent,
    PatientFormComponent,
    NoteFormComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
     { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
     { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
     AuthGard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
