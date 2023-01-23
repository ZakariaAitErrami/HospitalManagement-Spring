package ma.ensab.hospitalmanagement.web;

import lombok.AllArgsConstructor;
import ma.ensab.hospitalmanagement.services.MedicalService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RendezVousRestAPI {
    private MedicalService medicalService;
    public RendezVousRestAPI(MedicalService medicalService){
        this.medicalService=medicalService;
    }


}
