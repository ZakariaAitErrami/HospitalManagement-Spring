import { Medecin } from './../model/Medecin.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class MedecinService {

  constructor(private http:HttpClient) { }

  public getMedecinByName(name: string):Observable<Medecin>{
    return this.http.get<Medecin>(environment.backendHost+"/medecin/search?name="+name); //backendHost=http://localhost:8085
  }
  public getAllMedecin():Observable<Medecin>{
    return this.http.get<Medecin>(environment.backendHost+"/medecin");
  }
}
