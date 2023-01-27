import { RendezvousPatientComponent } from './rendezvous-patient/rendezvous-patient.component';
import { NewPatientComponent } from './new-patient/new-patient.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientsComponent } from './patients/patients.component';
import { RendezvousComponent } from './rendezvous/rendezvous.component';

const routes: Routes = [
  {path: "patients", component: PatientsComponent},
  {path: "rendezvous", component: RendezvousComponent},
  {path: "new-patient", component: NewPatientComponent},
  {path: "rendezvouspatient/:id", component: RendezvousPatientComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
