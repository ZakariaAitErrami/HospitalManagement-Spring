package ma.ensab.hospitalmanagement.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensab.hospitalmanagement.entities.RendezVous;

import javax.persistence.*;
import java.util.List;


@Data
public class MedecinDTO {

    private Long id;
    private String nomPr;
    private String specialite;
    private String email;

}
