import { Patient } from './../model/patient.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs'
import { environment } from 'src/environment/environment';
@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http:HttpClient) { }

  //get post put return an object of type Observable
  public getPatients():Observable<Array<Patient>>{
    return this.http.get<Array<Patient>>("http://localhost:8085/patients") //backendHost=http://localhost:8085/patients
  }

  public searchPatients(keyword: string): Observable<Array<Patient>>{
    return this.http.get<Array<Patient>>(environment.backendHost+"/patients/search?keyword="+keyword)
  }

  public savePatient(patient : Patient): Observable<Patient>{
    return this.http.post<Patient>(environment.backendHost+"/patients",patient)

  }

  public deletePatient(patientId : number){
    return this.http.delete<Patient>(environment.backendHost+"/patients/"+patientId)

  }

}
