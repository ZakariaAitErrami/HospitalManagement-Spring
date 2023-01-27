package ma.ensab.hospitalmanagement.repositories;

import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByPatient(Patient p);

    List<RendezVous> findAll();
    @Query("from RendezVous v")
    Page<RendezVous> getAllRendezVous(Pageable pageable);

    @Query("from RendezVous v where v.patient.nomPr like %:kw%")
    Page<RendezVous> getAllRendezPatient(@Param(value = "kw") String nom,Pageable pageable);

    @Query("from RendezVous v where v.patient.id =:kw")
    Page<RendezVous> getAllRendezPatientId(@Param(value = "kw") Long id,Pageable pageable);
}