package ma.ensab.hospitalmanagement.repositories;

import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByPatient(Patient p);

    List<RendezVous> findAll();
}