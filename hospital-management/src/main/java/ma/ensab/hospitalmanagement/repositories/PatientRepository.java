package ma.ensab.hospitalmanagement.repositories;

import ma.ensab.hospitalmanagement.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("from Patient p")
    Page<Patient> getAllPatient(PageRequest pageable);
    @Query("select p from Patient p where p.nomPr like :kw")
    List<Patient> searchPatient(@Param(value="kw") String keyword);
    List<Patient> findAll();


}
