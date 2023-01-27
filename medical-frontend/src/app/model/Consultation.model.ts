import { RendezVous } from './rendezvous.model';
export interface Consultation {
    id:                  number;
    rapportConsultation: string;
    prixConsultation:    number;
    rendezVousDTO: RendezVous;
}