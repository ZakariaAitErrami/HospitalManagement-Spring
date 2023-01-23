package ma.ensab.hospitalmanagement.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ensab.hospitalmanagement.dtos.PatientDTO;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.services.MedicalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class PatientController {

    private MedicalService medicalService;
    @GetMapping("/patients")
    public List<PatientDTO> patients(){

        return medicalService.listPatients();
    }
    @GetMapping("/patients/{id}")
    public PatientDTO getPatient(@PathVariable(name = "id") Long patientId) throws PatientNotFoundException {
        return medicalService.getPatient(patientId);

    }
    @PostMapping("/patients")
    public PatientDTO savePatient(@RequestBody PatientDTO patientDTO){
        return medicalService.savePatient(patientDTO);

    }
    @PutMapping("/patients/{patientId}")
    public PatientDTO updatePatient(@PathVariable Long patientId,@RequestBody PatientDTO patientDTO){
        patientDTO.setId(patientId);
        return medicalService.updatePatient(patientDTO);
    }

    @DeleteMapping("/patients/{patientId}")
    public void deletePatient(@PathVariable Long patientId){
        medicalService.deletePatient(patientId);
    }

}
