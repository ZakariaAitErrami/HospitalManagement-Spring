import { PatientService } from './../services/patient.service';
import { Patient } from './../model/patient.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-patient',
  templateUrl: './new-patient.component.html',
  styleUrls: ['./new-patient.component.css']
})
export class NewPatientComponent implements OnInit{
  newPatientFormGroup!: FormGroup;
  constructor(private fb: FormBuilder, private patientService :PatientService, private router: Router){}
  ngOnInit(): void {
    this.newPatientFormGroup=this.fb.group({
      nomPr: this.fb.control(null, [Validators.required, Validators.minLength(4)]),
      dateNaissance: this.fb.control(null, [Validators.required]),
      phoneNumber: this.fb.control("",[Validators.required, Validators.minLength(10)])

    });
    
  }


  handleSavePatient(){
    let patient: Patient = this.newPatientFormGroup.value;
    this.patientService.savePatient(patient).subscribe({
      next: data =>{
        alert('Patient has been saved successfully!')
        //this.newPatientFormGroup.reset();
        this.router.navigateByUrl('/patients')
      },
      error: err =>{
        console.log(err)
      }
    })
  }

}
