package ma.ensab.hospitalmanagement.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensab.hospitalmanagement.enums.RendezVousStatus;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateRendezVous;
    @Enumerated(EnumType.STRING)

    private RendezVousStatus status;
    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    private Patient patient;
    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;
}
