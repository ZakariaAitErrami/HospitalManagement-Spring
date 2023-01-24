package ma.ensab.hospitalmanagement.web;

import lombok.AllArgsConstructor;
import ma.ensab.hospitalmanagement.dtos.MedecinDTO;
import ma.ensab.hospitalmanagement.dtos.RendezVousDTO;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.services.MedicalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RendezVousRestAPI {
    private MedicalService medicalService;
    public RendezVousRestAPI(MedicalService medicalService){
        this.medicalService=medicalService;
    }


    @GetMapping("/rendezvous/patient/{idPatient}")
    public List<RendezVousDTO> getRendezVousPatient(@PathVariable Long idPatient) throws PatientNotFoundException {
        return medicalService.rdvsPatient(idPatient);
    }

    @GetMapping("/allrendezvous")
    public List<RendezVousDTO> getallrdv(){
        return medicalService.listRendezVous();
    }
    @GetMapping("/medecins")
    public List<MedecinDTO> getAllMedecin(){
        return medicalService.listMedecin();
    }





}
