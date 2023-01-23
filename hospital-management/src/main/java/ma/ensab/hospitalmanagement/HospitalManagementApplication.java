package ma.ensab.hospitalmanagement;

import ma.ensab.hospitalmanagement.entities.Consultation;
import ma.ensab.hospitalmanagement.entities.Medecin;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import ma.ensab.hospitalmanagement.enums.RendezVousStatus;
import ma.ensab.hospitalmanagement.repositories.ConsultationRepository;
import ma.ensab.hospitalmanagement.repositories.MedecinRepository;
import ma.ensab.hospitalmanagement.repositories.PatientRepository;
import ma.ensab.hospitalmanagement.repositories.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);
	}


	//@Bean
	CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository,
							ConsultationRepository consultationRepository){
		return args -> {
			Stream.of("Zakaria", "Khaoula", "Badr", "Aicha").forEach(name -> {
				Patient patient= new Patient();
				patient.setNomPr(name);
				patient.setPhoneNumber("0612974703");
				patient.setDateNaissance(new Date());
				patientRepository.save(patient);

			});

			Stream.of("Adil","Amine","Hamid").forEach(med->{
				Medecin medecin = new Medecin();
				medecin.setEmail(med+"@gmail.com");
				medecin.setNomPr(med);
				medecin.setSpecialite("Gastro");
				medecinRepository.save(medecin);
			});

			patientRepository.findAll().forEach(patient -> {
				RendezVous rendezVous = new RendezVous();
				rendezVous.setDateRendezVous(new Date());
				rendezVous.setPatient(patient);
				rendezVous.setStatus(RendezVousStatus.PENDING);
				rendezVous.setMedecin(medecinRepository.findByNomPr("Hamid"));
				rendezVousRepository.save(rendezVous);
			});

			rendezVousRepository.findAll().forEach(rend -> {
				Consultation consultation = new Consultation();
				consultation.setRendezVous(rend);
				consultation.setPrixConsultation(300.0);
				consultation.setRapportConsultation("Very good");
				consultationRepository.save(consultation);

			});
		};
	}
}
