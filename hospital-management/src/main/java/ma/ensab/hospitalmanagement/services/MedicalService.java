package ma.ensab.hospitalmanagement.services;

import ma.ensab.hospitalmanagement.dtos.PatientDTO;
import ma.ensab.hospitalmanagement.entities.Consultation;
import ma.ensab.hospitalmanagement.entities.Medecin;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import ma.ensab.hospitalmanagement.exceptions.MedecinNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.RendezVousNotFoundException;

import java.util.*;

public interface MedicalService {
    PatientDTO savePatient(PatientDTO patientDTO);
    RendezVous saveRendezVous(Date dateRendezVous, Long idPatient, String nomMedecin) throws PatientNotFoundException;
    Medecin saveMedecin(Medecin medecin);
    Consultation saveConsultation(Long idRdv,double prixConsultation, String rapportConsultation) throws RendezVousNotFoundException;
    List<PatientDTO> listPatients();
    List<RendezVous> listRendezVous();
    List<RendezVous> rdvsPatient(Long idPatient) throws PatientNotFoundException;
    Medecin searchMedecinByName(String name) throws MedecinNotFoundException;

    PatientDTO getPatient(Long id) throws PatientNotFoundException;

    PatientDTO updatePatient(PatientDTO patientDTO);

    void deletePatient(Long patientId);
}
