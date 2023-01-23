package ma.ensab.hospitalmanagement.mappers;

import ma.ensab.hospitalmanagement.dtos.PatientDTO;
import ma.ensab.hospitalmanagement.entities.Patient;
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
}
