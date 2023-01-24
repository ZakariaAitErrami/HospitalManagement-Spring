package ma.ensab.hospitalmanagement.mappers;

import ma.ensab.hospitalmanagement.dtos.ConsultationDTO;
import ma.ensab.hospitalmanagement.dtos.MedecinDTO;
import ma.ensab.hospitalmanagement.dtos.PatientDTO;
import ma.ensab.hospitalmanagement.dtos.RendezVousDTO;
import ma.ensab.hospitalmanagement.entities.Consultation;
import ma.ensab.hospitalmanagement.entities.Medecin;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

//MapStruct
@Service
public class MedicalMapperImpl {

    public PatientDTO fromPatient(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        /*patientDTO.setId(patient.getId());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDTO.setNomPr(patient.getNomPr());
        patientDTO.setDateNaissance(patient.getDateNaissance());*/
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    public Patient fromPatientDTO(PatientDTO patientDTO){
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        return patient;
    }

//    public MedecinDTO fromMededin(Medecin medecin){
//        MedecinDTO medecinDTO = new MedecinDTO();
//        BeanUtils.copyProperties(medecin, medecinDTO);
//        return medecinDTO;
//    }
//
//    public Medecin fromMedecinDTO(MedecinDTO medecinDTO){
//        Medecin medecin = new Medecin();
//        BeanUtils.copyProperties(medecinDTO, medecin);
//        return medecin;
//    }
public MedecinDTO fromMedecin(Medecin medecin){
    MedecinDTO medecinDTO = new MedecinDTO();
    medecinDTO.setId(medecin.getId());
    medecinDTO.setNomPr(medecin.getNomPr());
    medecinDTO.setSpecialite(medecin.getSpecialite());
    medecinDTO.setEmail(medecin.getEmail());
    return medecinDTO;
}

    public Medecin fromMedecinDTO(MedecinDTO medecinDTO){
        Medecin medecin = new Medecin();
        medecin.setId(medecinDTO.getId());
        medecin.setNomPr(medecinDTO.getNomPr());
        medecin.setSpecialite(medecinDTO.getSpecialite());
        medecin.setEmail(medecinDTO.getEmail());
        return medecin;
    }

    public ConsultationDTO fromConsultation(Consultation consultation){
        ConsultationDTO consultationDTO = new ConsultationDTO();
        BeanUtils.copyProperties(consultation, consultationDTO);
        consultationDTO.setRendezVousDTO(fromRendezVous(consultation.getRendezVous()));
        return consultationDTO;
    }

    public Consultation fromConsultationDTO(ConsultationDTO consultationDTO){
        Consultation consultation = new Consultation();
        BeanUtils.copyProperties(consultationDTO, consultation);
        consultation.setRendezVous(fromRendezVousDTO(consultationDTO.getRendezVousDTO()));
        return consultation;
    }


    /*public RendezVousDTO fromRendezVous(RendezVous rendezVous){

        RendezVousDTO rendezVousDTO = new RendezVousDTO();
        //BeanUtils.copyProperties(rendezVous, rendezVousDTO);

        rendezVousDTO.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDTO.setId(rendezVous.getId());
        rendezVousDTO.setStatus(rendezVous.getStatus());
        rendezVousDTO.setMedecinDTO(fromMededin(rendezVous.getMedecin()));
        rendezVousDTO.setPatientDTO(fromPatient(rendezVous.getPatient()));
        rendezVousDTO.setConsultationDTO(fromConsultation(rendezVous.getConsultation()));
        return rendezVousDTO;
    }
    public RendezVous fromRendezVousDTO(RendezVousDTO rendezVousDTO){

        RendezVous rendezVous = new RendezVous();
        BeanUtils.copyProperties(rendezVousDTO, rendezVous);

        rendezVous.setPatient(fromPatientDTO(rendezVousDTO.getPatientDTO()));
        rendezVous.setMedecin(fromMedecinDTO(rendezVousDTO.getMedecinDTO()));
        rendezVous.setConsultation(fromConsultationDTO(rendezVousDTO.getConsultationDTO()));
        return rendezVous;
    }*/
    public RendezVousDTO fromRendezVous(RendezVous rendezVous){
        RendezVousDTO rendezVousDTO = new RendezVousDTO();
        rendezVousDTO.setId(rendezVous.getId());
        rendezVousDTO.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDTO.setStatus(rendezVous.getStatus());
        rendezVousDTO.setMedecinDTO(fromMedecin(rendezVous.getMedecin()));
        rendezVousDTO.setPatientDTO(fromPatient(rendezVous.getPatient()));
       // rendezVousDTO.setConsultationDTO(fromConsultation(rendezVous.getConsultation()));
        return rendezVousDTO;
    }

    public RendezVous fromRendezVousDTO(RendezVousDTO rendezVousDTO){
        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(rendezVousDTO.getId());
        rendezVous.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        rendezVous.setStatus(rendezVousDTO.getStatus());
        rendezVous.setMedecin(fromMedecinDTO(rendezVousDTO.getMedecinDTO()));
        rendezVous.setPatient(fromPatientDTO(rendezVousDTO.getPatientDTO()));
       // rendezVous.setConsultation(fromConsultationDTO(rendezVousDTO.getConsultationDTO()));
        return rendezVous;
    }

}
