package ma.ensab.hospitalmanagement.repositories;

import ma.ensab.hospitalmanagement.entities.Consultation;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query("select c from Consultation c where c.rendezVous.id =:idrdv")
    Consultation getConsultationByIdRdv(@Param(value = "idrdv") Long idrdv);
}
