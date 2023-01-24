package ma.ensab.hospitalmanagement.repositories;

import ma.ensab.hospitalmanagement.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("from Patient p")
    Page<Patient> getAllPatient(PageRequest pageable);
    List<Patient> findAll();


}
