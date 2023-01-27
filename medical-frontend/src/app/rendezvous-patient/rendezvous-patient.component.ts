import { RendezVousDetails } from './../model/rendezvous.model';
import { Observable } from 'rxjs';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Patient } from './../model/patient.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RendezvousService } from '../services/rendezvous.service';

@Component({
  selector: 'app-rendezvous-patient',
  templateUrl: './rendezvous-patient.component.html',
  styleUrls: ['./rendezvous-patient.component.css']
})
export class RendezvousPatientComponent implements OnInit{
  patientId!: number;
  patient!: Patient;
  rdvFormGroup!: FormGroup;
  currentPage: number=0;
  pageSize: number=3;
  rdvObservable!: Observable<RendezVousDetails>;

  constructor(private rendezVousService: RendezvousService,private fb: FormBuilder,private route: ActivatedRoute, private router: Router){
    this.patient=this.router.getCurrentNavigation()?.extras.state as Patient;

  }
  ngOnInit(): void {
   this.patientId=this.route.snapshot.params['id'];//get id via url
   this.rdvFormGroup=this.fb.group({
      dateRendezVous:  this.fb.control(null),
      nomMedecin: this.fb.control(''),
      //status: this.fb.control('PENDING')
    });
    this.getRendezVousPatient();

  }

  getRendezVousPatient(){
    this.rdvObservable = this.rendezVousService.getRendezVousPatientById(this.patientId,this.currentPage, this.pageSize);
  }

  gotoPage(page: number){
    this.currentPage=page;
    this.getRendezVousPatient();
  }

  handleSaveRdv(){

  }
  
}
