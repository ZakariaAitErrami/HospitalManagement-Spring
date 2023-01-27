package ma.ensab.hospitalmanagement.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ensab.hospitalmanagement.dtos.MedecinDTO;
import ma.ensab.hospitalmanagement.dtos.PatientDTO;
import ma.ensab.hospitalmanagement.dtos.PatientPageDTO;
import ma.ensab.hospitalmanagement.dtos.RendezVousPageDTO;
import ma.ensab.hospitalmanagement.entities.Medecin;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.exceptions.MedecinNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.services.MedicalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PatientController {

    private MedicalService medicalService;
    @GetMapping("/patients")
    public List<PatientDTO> patients(){

        return medicalService.listPatients();
    }

    @GetMapping("/patientspage/search")
    public PatientPageDTO searchPatient(@RequestParam(name="keyword", defaultValue = "") String keyword,
                                          @RequestParam(name="page", defaultValue = "0") int page,
                                          @RequestParam(name="size", defaultValue = "5")int size){

        return medicalService.searchPatientt(keyword,page, size);
    }

    @GetMapping("/patientspage")
    public PatientPageDTO patientspage(
                                      @RequestParam(name="page", defaultValue = "0") int page,
                                      @RequestParam(name="size", defaultValue = "5")int size){

        return medicalService.listPatientspage(page, size);
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

    /*Medecin*/

    @GetMapping("/medecin/search")
    public MedecinDTO searchMedecin(@RequestParam String name) throws MedecinNotFoundException {
        return medicalService.searchMedecinByName(name);
    }

    @GetMapping("/medecin")
    public List<MedecinDTO> listMedecin() {
        return medicalService.listMedecin();
    }



}
