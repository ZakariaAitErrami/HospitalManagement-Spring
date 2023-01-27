package ma.ensab.hospitalmanagement.web;

import lombok.AllArgsConstructor;
import ma.ensab.hospitalmanagement.dtos.*;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.services.MedicalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RendezVousRestAPI {
    private MedicalService medicalService;
    public RendezVousRestAPI(MedicalService medicalService){
        this.medicalService=medicalService;
    }


    @GetMapping("/rendezvouss/patient/{idPatient}")
    public List<RendezVousDTO> getRendezVousPatient(@PathVariable Long idPatient) throws PatientNotFoundException {
        return medicalService.rdvsPatient(idPatient);
    }
    @GetMapping("/allrendezvous")
    public RendezVousPageDTO getallrdv(@RequestParam(name="page", defaultValue = "0") int page,
                                       @RequestParam(name="size", defaultValue = "5")int size){
        return medicalService.listofRendezvous(page, size);

        //return medicalService.listRendezVous();
    }
    @GetMapping("/medecins")
    public List<MedecinDTO> getAllMedecin(){
        return medicalService.listMedecin();
    }

    @GetMapping("/rendezvous/search")
    public RendezVousPageDTO searchPatient(@RequestParam(name="keyword", defaultValue = "") String keyword,
                                        @RequestParam(name="page", defaultValue = "0") int page,
                                        @RequestParam(name="size", defaultValue = "5")int size){

        return medicalService.searchRendezVousPatient(keyword,page, size);
    }


    @GetMapping("/rendezvous/patient/{idPatient}")
    public RendezVousPageDTO getRendezVousPatientId(@PathVariable Long idPatient,
                                                    @RequestParam(name="page", defaultValue = "0") int page,
                                                    @RequestParam(name="size", defaultValue = "5")int size) throws PatientNotFoundException {
        return medicalService.rdvPatient(idPatient, page, size);
    }

    @PostMapping("/saverdv")
    public RendezVousDTO saveRendezVous(@RequestBody RendezVousDTO rendezVousDTO){
        return medicalService.saveRendezVous(rendezVousDTO);

    }


}
