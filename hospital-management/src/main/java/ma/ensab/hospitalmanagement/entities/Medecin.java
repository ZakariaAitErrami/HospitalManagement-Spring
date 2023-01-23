package ma.ensab.hospitalmanagement.entities;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Medecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomPr;
    private String specialite;
    private String email;
    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVous;

}
