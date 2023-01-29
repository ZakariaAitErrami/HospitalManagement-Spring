import { Consultationclass, RendezVousclass, Patientclass, Medecinclass } from './../model/classes.model';
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

  rapportConsultation: string="";
  prixConsultation: number=0;
  patientId!: number;
  patient!: Patient;
  medecinObservable!: Observable<Medecin>;
  consultationObservable!: Observable<Consultation>;
  rendezvousObservable!: Observable<RendezVous>;

  medecin!: Medecin;
  rdv!: RendezVous;
  length: number=0;
  rdvFormGroup!: FormGroup;
  consultFormGroup!: FormGroup;
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

    this.consultFormGroup=this.fb.group({
      rapportConsultation: this.fb.control(''),
      prixConsultation: this.fb.control(0)
    })

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

  // getConsultation(idRdv: number): Observable<Consultation> {
  //    this.consultationObservable = this.rendezVousService.getConsultation(idRdv);
  //     return this.consultationObservable;
  // }

  seeConsultation(idRdv: number){
    
    
  }

  addConsultation(): void {

    this.rendezVousService.getRdvById(this.id).subscribe({
      next: (r: RendezVous)=>{

        let c: Consultation=this.consultFormGroup.value;
        c.rendezVousDTO=r;
        //save
        this.rendezVousService.saveConsultation(c).subscribe(cc=>{
          alert("Consultation added!");
          this.consultFormGroup.reset();
          this.getRendezVousPatient();
        })


      },
      error: (err)=>{
        console.log(err)
      }
    }

    )

  }

  //attribute od RendezVous
  id: number=0;
  daterdv: Date=new Date();
  statusrdv: string='PENDING';
  hasconsultation: boolean=false;
    //medecin
  idm: number=0;
  nomM: string="";
  specialite: string="";
  email:string="";
    //patient
  idp: number=0;
  nomP: string="";
  dateNaiss: Date=new Date();
  phone: string="";


  public onOpenModal(op : RendezVous,mode: string): void{
    
    this.id=op.id;
    this.daterdv=op.dateRendezVous;
    this.statusrdv=op.status;
    this.hasconsultation=op.hasConsultation;
    this.idm=op.medecinDTO.id;
    this.nomM=op.medecinDTO.nomPr;
    this.specialite=op.medecinDTO.specialite;
    this.email=op.medecinDTO.email;
    this.idp=op.patientDTO.id;
    this.nomP=op.patientDTO.nomPr;
    this.dateNaiss=op.patientDTO.dateNaissance;
    this.phone=op.patientDTO.phoneNumber;
   

    const container= document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display='none';
    button.setAttribute('data-bs-toggle','modal');
    if(mode==='add'){
      button.setAttribute('data-bs-target','#addConsultModal');
    }
    if(mode==='see'){
       button.setAttribute('data-bs-target','#seeconsultation');
       this.consultationObservable = this.rendezVousService.getConsultation(op.id);
    }
    

    container?.appendChild(button);
    button.click();
    //this.addConsultation(op);

  }



}
