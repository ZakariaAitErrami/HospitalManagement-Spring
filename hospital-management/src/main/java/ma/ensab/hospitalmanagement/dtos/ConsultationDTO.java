package ma.ensab.hospitalmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensab.hospitalmanagement.entities.RendezVous;

import javax.persistence.*;



@Data
public class ConsultationDTO {

    private Long id;
    private String rapportConsultation;
    private double prixConsultation;

    private RendezVousDTO rendezVousDTO;

}
