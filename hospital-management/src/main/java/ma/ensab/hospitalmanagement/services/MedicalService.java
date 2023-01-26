package ma.ensab.hospitalmanagement.services;

import ma.ensab.hospitalmanagement.dtos.*;
import ma.ensab.hospitalmanagement.entities.Consultation;
import ma.ensab.hospitalmanagement.entities.Medecin;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import ma.ensab.hospitalmanagement.exceptions.MedecinNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.RendezVousNotFoundException;
import org.springframework.data.domain.PageRequest;

import java.util.*;

public interface MedicalService {
    PatientDTO savePatient(PatientDTO patientDTO);
    RendezVousDTO saveRendezVous(Date dateRendezVous, Long idPatient, String nomMedecin) throws PatientNotFoundException;
    MedecinDTO saveMedecin(MedecinDTO medecinDTO);
    ConsultationDTO saveConsultation(Long idRdv, double prixConsultation, String rapportConsultation) throws RendezVousNotFoundException;
    List<PatientDTO> listPatients();
    List<RendezVousDTO> listRendezVous();

    List<RendezVous> listRendezVouss();

    List<RendezVousDTO> rdvsPatient(Long idPatient) throws PatientNotFoundException;
    MedecinDTO searchMedecinByName(String name) throws MedecinNotFoundException;

    PatientDTO getPatient(Long id) throws PatientNotFoundException;

    PatientDTO updatePatient(PatientDTO patientDTO);

    void deletePatient(Long patientId);

    List<MedecinDTO> listMedecin();


    PatientPageDTO listPatientspage(int page, int size);

    //List<PatientDTO> searchPatient(String keyword);
    PatientPageDTO searchPatientt(String keyword,int page, int size);

}
