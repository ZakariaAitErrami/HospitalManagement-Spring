package ma.ensab.hospitalmanagement;

import ma.ensab.hospitalmanagement.dtos.RendezVousDTO;
import ma.ensab.hospitalmanagement.entities.Consultation;
import ma.ensab.hospitalmanagement.entities.Medecin;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import ma.ensab.hospitalmanagement.enums.RendezVousStatus;
import ma.ensab.hospitalmanagement.exceptions.MedecinNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.RendezVousNotFoundException;
import ma.ensab.hospitalmanagement.repositories.ConsultationRepository;
import ma.ensab.hospitalmanagement.repositories.MedecinRepository;
import ma.ensab.hospitalmanagement.repositories.PatientRepository;
import ma.ensab.hospitalmanagement.repositories.RendezVousRepository;
import ma.ensab.hospitalmanagement.services.MedicalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);
	}


	/*@Bean
	CommandLineRunner commandLineRun(RendezVousRepository medicalService){
		return args -> {
			List<RendezVous> rdvs = medicalService.findAll();
			for (RendezVous rdv: rdvs){
				System.out.println(rdv.getId());
			}
		};
	}*/

	//@Bean
	CommandLineRunner commandLineRunner(MedicalService medicalService){
		return args -> {
			List<RendezVousDTO> rdvs = medicalService.rdvsPatient(6L);
			for(RendezVousDTO r: rdvs){
				System.out.println(r.getId());
				System.out.println(r.getStatus());

				System.out.println("------");
			}

			medicalService.listRendezVous().forEach(rdv->{

				try {
					medicalService.saveConsultation(rdv.getId(), 350.0,"very good!");
				} catch (RendezVousNotFoundException e) {
					throw new RuntimeException(e);
				}

			});
			/*Stream.of("Karim","Yassine","Ali").forEach(name->{
				Patient p = new Patient();
				p.setNomPr(name);
				p.setPhoneNumber("06424238989");
				p.setDateNaissance(new Date());

				medicalService.savePatient(p);

			});

			medicalService.listPatients().forEach(patient -> {
				for(int i=0;i<3; i++){
					//Date dateRendezVous, Long idPatient, String nomMedecin
					RendezVous rdv = new RendezVous();


					rdv.setPatient(patient);
					rdv.setDateRendezVous(new Date());
					rdv.setStatus(RendezVousStatus.PENDING);
					try {
						medicalService.saveRendezVous(new Date(), patient.getId(), "Adil");
					} catch (PatientNotFoundException e) {
						throw new RuntimeException(e);
					}


				}
			});*/

		};
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
