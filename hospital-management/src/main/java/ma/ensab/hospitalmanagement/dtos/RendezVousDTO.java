package ma.ensab.hospitalmanagement.dtos;

import lombok.Data;

import ma.ensab.hospitalmanagement.enums.RendezVousStatus;

import javax.persistence.*;
import java.util.Date;


@Data
public class RendezVousDTO {

    private Long id;

    private Date dateRendezVous;
    private RendezVousStatus status;

    private MedecinDTO medecinDTO;

    private PatientDTO patientDTO;
    private ConsultationDTO consultationDTO;
}
