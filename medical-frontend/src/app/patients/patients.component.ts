import { Observable,catchError,throwError, map } from 'rxjs';
import { PatientService } from './../services/patient.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../model/patient.model';
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  patients! : Observable<Array<Patient>>;
  errorMessage!: string;
  searchFormGroup: FormGroup | undefined;
  constructor(private patientService: PatientService, private fb: FormBuilder){

  }

   ngOnInit(): void {
      this.searchFormGroup=this.fb.group({
        keyword: this.fb.control("")
      })
      this.patients=this.patientService.getPatients().pipe(
        catchError(err => {
          this.errorMessage=err.message;
          return throwError(err);
      })
      )
        
      // this.patientService.getPatients().subscribe({
      //   next: (data)=>{
      //     this.patients=data;
      //   },
      //   error: (err)=>{
      //     this.errorMessage=err.message;
      //   }
      // })
      
    }


    handleSearchPatient(){
      let kw = this.searchFormGroup?.value.keyword;
      this.patients=this.patientService.searchPatients(kw).pipe(
        catchError(err => {
          this.errorMessage=err.message;
          return throwError(err);
      })
      )

    }

    handleDeletePatient(p: Patient){
      this.patientService.deletePatient(p.id).subscribe({
        next: (resp)=>{
          //handleSearch()
          this.patients=this.patients.pipe(
            map(data => {
              let index=data.indexOf(p);
              data.slice(index,1)
              return data;
            })
          );
        },
        error: err=>{
          console.log(err)
        }
      })
    }




  // ngOnInit(): void {
      // this.http.get("http://localhost:8085/patients").subscribe({
      //   next: (data)=>{
      //     this.patients=data;
      //   },
      //   error: (err)=>{
      //     console.log(err);
      //   }
      // })
    
  // }


  

}
