import { Consultation } from './../model/Consultation.model';
import { MedecinService } from './../services/medecin.service';
import { Medecin } from './../model/Medecin.model';
import { RendezVous, RendezVousDetails } from './../model/rendezvous.model';
import { Observable, throwError, catchError, async, map } from 'rxjs';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Patient } from './../model/patient.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RendezvousService } from '../services/rendezvous.service';
import { isEmpty } from 'rxjs/operators';
@Component({
  selector: 'app-rendezvous-patient',
  templateUrl: './rendezvous-patient.component.html',
  styleUrls: ['./rendezvous-patient.component.css']
})
export class RendezvousPatientComponent implements OnInit{
  patientId!: number;
  patient!: Patient;
  medecinObservable!: Observable<Medecin>;
  consultationObservable!: Observable<Consultation>;
  consultation!: Consultation;

  medecin!: Medecin;
  rdv!: RendezVous;
  length: number=0;
  rdvFormGroup!: FormGroup;
  currentPage: number=0;
  pageSize: number=3;
  rdvObservable!: Observable<RendezVousDetails>;
  medecinsObservable!: Observable<Array<Medecin>>;

  constructor(private medecinService: MedecinService,private rendezVousService: RendezvousService,private fb: FormBuilder,private route: ActivatedRoute, private router: Router){
    this.patient=this.router.getCurrentNavigation()?.extras.state as Patient;

  }
  ngOnInit(): void {
   this.patientId=this.route.snapshot.params['id'];//get id via url
   this.rdvFormGroup=this.fb.group({
      dateRendezVous:  this.fb.control(null),
      nomMedecin: this.fb.control(''),
      status: this.fb.control('PENDING')
    });
    this.getRendezVousPatient();

    this.medecinsObservable = this.medecinService.getAllMedecin();


  }

  getRendezVousPatient(){
    this.rdvObservable = this.rendezVousService.getRendezVousPatientById(this.patientId,this.currentPage, this.pageSize);
  }

  gotoPage(page: number){
    this.currentPage=page;
    this.getRendezVousPatient();
  }

  handleSaveRdv(){ 
    let rendezvous: RendezVous = this.rdvFormGroup.value;
    this.medecinService.getMedecinByName(this.rdvFormGroup.get('nomMedecin')?.value)
    .subscribe(medecin => {
      rendezvous.medecinDTO = medecin;
      rendezvous.patientDTO = this.patient;
    
    this.rendezVousService.saveRdv(rendezvous).subscribe(rdv => {
      alert("Rendez-vous added!");
      this.rdvFormGroup.reset();
      this.getRendezVousPatient();
},);
  });
  }

  getConsultation(idRdv: number): Observable<Consultation> {
     this.consultationObservable = this.rendezVousService.getConsultation(idRdv);
      return this.consultationObservable;
  }

  seeConsultation(){

  }

  addConsultation(){

  }



}
