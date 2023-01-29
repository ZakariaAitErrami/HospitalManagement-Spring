import { Consultation } from './../model/Consultation.model';
import { RendezVous } from './../model/rendezvous.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { RendezVousDetails } from '../model/rendezvous.model';
import { Consultationclass } from '../model/classes.model';


@Injectable({
  providedIn: 'root'
})
export class RendezvousService {

  constructor(private http:HttpClient) { }

  public getAllRendezVous(page: number, size: number):Observable<RendezVousDetails>{
    return this.http.get<RendezVousDetails>(environment.backendHost+"/allrendezvous?page="+page+"&size="+size) //backendHost=http://localhost:8085
  }

  public getRendezVousPatientByName(keyword: string,page: number, size: number):Observable<RendezVousDetails>{
    return this.http.get<RendezVousDetails>(environment.backendHost+"/rendezvous/search?keyword="+keyword+"&page="+page+"&size="+size);
  }
  //http://localhost:8085/rendezvous/patient/1?page=2&size=2
  public getRendezVousPatientById(idpatient: number,page: number, size: number):Observable<RendezVousDetails>{
    return this.http.get<RendezVousDetails>(environment.backendHost+"/rendezvous/patient/"+idpatient+"?page="+page+"&size="+size);
  }

   public saveRdv(rendezvous : RendezVous): Observable<RendezVous>{
     return this.http.post<RendezVous>(environment.backendHost+"/saverdv",rendezvous)

   }

   public getConsultation(idRdv: number): Observable<Consultation>{
    return this.http.get<Consultation>(environment.backendHost+"/consultation/"+idRdv)
   }

   public saveConsultation(consultation:Consultation): Observable<Consultation>{
    return this.http.post<Consultation>(environment.backendHost+"/saveconsultation", consultation)
   }

   public getRdvById(idrdv: number): Observable<RendezVous>{
    return this.http.get<RendezVous>(environment.backendHost+"/rendezvous/"+idrdv)
   }

}
