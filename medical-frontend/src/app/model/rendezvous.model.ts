import { Consultation } from "./Consultation.model";
import { Medecin } from "./Medecin.model";
import { Patient } from "./patient.model";

export interface RendezVousDetails {
    rendezVousDTOS: RendezVous[];
    currentPage:    number;
    totalPages:     number;
    pageSize:       number;
}

export interface RendezVous {
    id:              number;
    dateRendezVous:  Date;
    status:          string;
    medecinDTO:      Medecin;
    patientDTO:      Patient;
    hasConsultation: boolean;
    //consultationDTO: Consultation;
}



