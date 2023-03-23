import { NoteFormComponent } from './components/note-form/note-form.component';
import { PatientDisplayComponent } from './components/patient-display/patient-display.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { PatientFormComponent } from './components/patient-form/patient-form.component';

const routes: Routes = [
  {path:'', component:PatientListComponent},
  {path:'patient/add', component:PatientFormComponent},
  {path:'patient/:id', component:PatientDisplayComponent},
  {path:'patient/edit/:id', component:PatientFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
