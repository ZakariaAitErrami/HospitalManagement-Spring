export class Patientclass {
    public constructor(id: number, nomPr: string, dateNaissance: Date, phoneNumber: string){}
}


export class Medecinclass {
    public constructor(id: number, nomPr: string, specialite: string,  email: string){}   
}
export class RendezVousclass {
    public constructor(id: number, dateRendezVous: Date, status: string,
         hasConsultation:boolean,
        medecinDTO: Medecinclass,patientDTO: Patientclass){}
}
export class Consultationclass {
    id: number=0;
    rapportConsultation: string="";
    prixConsultation: number=0;
    rendezVousDTO!: RendezVousclass;
   // public constructor(rapportConsultation: string,prixConsultation: number, rendezVousDTO: RendezVousclass){}
}
