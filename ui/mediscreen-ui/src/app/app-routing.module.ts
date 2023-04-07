import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { PatientDisplayComponent } from './components/patient-display/patient-display.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { PatientFormComponent } from './components/patient-form/patient-form.component';
import { AuthGard } from './guards/auth-gard';
import { ErrorComponent } from './components/error/error.component';

const routes: Routes = [
  {path:'', component:PatientListComponent, canActivate: [AuthGard]},
  {path: 'login', component:LoginComponent},
  {path: 'register', component:RegistrationComponent},
  {path: 'patient/add', component:PatientFormComponent, canActivate: [AuthGard]},
  {path: 'patient/:id', component:PatientDisplayComponent, canActivate: [AuthGard]},
  {path: 'patient/edit/:id', component:PatientFormComponent, canActivate: [AuthGard]},
  {path: 'error', component:ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
