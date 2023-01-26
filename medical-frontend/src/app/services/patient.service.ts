import { Patient, PatientDetails } from './../model/patient.model';
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
  public getPatients(page: number, size: number):Observable<PatientDetails>{
    return this.http.get<PatientDetails>(environment.backendHost+"/patientspage?page="+page+"&size="+size) //backendHost=http://localhost:8085
  }

  public searchPatients(keyword: string,page: number, size: number): Observable<PatientDetails>{
    return this.http.get<PatientDetails>(environment.backendHost+"/patientspage/search?keyword="+keyword+"&page="+page+"&size"+size)
  }

  public savePatient(patient : Patient): Observable<Patient>{
    return this.http.post<Patient>(environment.backendHost+"/patients",patient)

  }

  public deletePatient(patientId : number){
    return this.http.delete<Patient>(environment.backendHost+"/patients/"+patientId)

  }

}
