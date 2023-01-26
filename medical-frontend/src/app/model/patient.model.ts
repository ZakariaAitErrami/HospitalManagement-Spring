export interface PatientDetails {
    patientDTOS: Patient[];
    currentPage: number;
    totalPages:  number;
    pageSize:    number;
}

export interface Patient {
    id:            number;
    nomPr:         string;
    dateNaissance: Date;
    phoneNumber:   string;
}
