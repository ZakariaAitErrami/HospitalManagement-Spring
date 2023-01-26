import { RendezVousDetails } from './../model/rendezvous.model';
import { Component, OnInit } from '@angular/core';
import { RendezvousService } from '../services/rendezvous.service';
import { Observable, catchError, throwError } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-rendezvous',
  templateUrl: './rendezvous.component.html',
  styleUrls: ['./rendezvous.component.css']
})
export class RendezvousComponent implements OnInit {
  rendezVousObservable! : Observable<RendezVousDetails>;
  searchFormGroup: FormGroup | undefined;
  currentPage: number=0;
  pageSize: number=5;

  errorMessage!: string;
  constructor(private rendezVousService: RendezvousService,private fb: FormBuilder){}

  ngOnInit(): void {

    this.searchFormGroup=this.fb.group({
      keyword: this.fb.control("")
    })

      this.rendezVousObservable=this.rendezVousService.getAllRendezVous(this.currentPage, this.pageSize).pipe(
        catchError(err=>{
          this.errorMessage=err.message;
          return throwError(err);
        })
      )
    

  }

//   handleSearchPatient(){
//     let kw = this.searchFormGroup?.value.keyword;
//     this.patientsObservable=this.patientService.searchPatients(kw,this.currentPage,this.pageSize).pipe(
//      catchError(err => {
//        this.errorMessage=err.message;
//        return throwError(err);
//    })
//    )

//  }
  handleSearchRendezVous(){
    let kw = this.searchFormGroup?.value.keyword;
    this.rendezVousObservable=this.rendezVousService.getRendezVousPatientByName(kw, this.currentPage, this.pageSize).pipe(
      catchError(err=>{
        this.errorMessage=err.message;
        return throwError(err);
      })
    )
  }

  gotoPage(page: number){
    this.currentPage=page;
    this.handleSearchRendezVous();
  }
  

}
