import { PatientDetails } from './../model/patient.model';
import { Observable,catchError,throwError, map } from 'rxjs';
import { PatientService } from './../services/patient.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../model/patient.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  patientsObservable! : Observable<PatientDetails>;
  currentPage: number=0;
  pageSize: number=5;

  errorMessage!: string;
  searchFormGroup: FormGroup | undefined;
  constructor(private patientService: PatientService, private fb: FormBuilder,private router: Router){

  }

   ngOnInit(): void {
      this.searchFormGroup=this.fb.group({
        keyword: this.fb.control("")
      })
      this.patientsObservable=this.patientService.getPatients(this.currentPage,this.pageSize).pipe(
        catchError(err => {
          this.errorMessage=err.message;
          return throwError(err);
      })
      )
        
      
    }
    gotoPage(page: number){
      this.currentPage=page;
      this.handleSearchPatient();
    }


     handleSearchPatient(){
       let kw = this.searchFormGroup?.value.keyword;
       this.patientsObservable=this.patientService.searchPatients(kw,this.currentPage,this.pageSize).pipe(
        catchError(err => {
          this.errorMessage=err.message;
          return throwError(err);
      })
      )

    }


    handleDeletePatient(p: Patient){
      let conf = confirm("Are you sure?");
      if(!conf) return;
      this.patientService.deletePatient(p.id).subscribe({
        next: (resp)=>{
          this.handleSearchPatient();
        },
        error: err=>{
          this.errorMessage=err;
        }
      })
    }

    handleRdvPatientid(p: Patient){
      this.router.navigateByUrl("/rendezvouspatient/"+p.id,{state:p});
      //this.router.navigateByUrl("/rendezvouspatient/"+p.id, {state:p});
    }







  

}
