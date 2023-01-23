package ma.ensab.hospitalmanagement.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ensab.hospitalmanagement.dtos.PatientDTO;
import ma.ensab.hospitalmanagement.entities.Consultation;
import ma.ensab.hospitalmanagement.entities.Medecin;
import ma.ensab.hospitalmanagement.entities.Patient;
import ma.ensab.hospitalmanagement.entities.RendezVous;
import ma.ensab.hospitalmanagement.enums.RendezVousStatus;
import ma.ensab.hospitalmanagement.exceptions.MedecinNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.PatientNotFoundException;
import ma.ensab.hospitalmanagement.exceptions.RendezVousNotFoundException;
import ma.ensab.hospitalmanagement.mappers.MedicalMapperImpl;
import ma.ensab.hospitalmanagement.repositories.ConsultationRepository;
import ma.ensab.hospitalmanagement.repositories.MedecinRepository;
import ma.ensab.hospitalmanagement.repositories.PatientRepository;
import ma.ensab.hospitalmanagement.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MedicalServiceImp implements MedicalService{

    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    private MedicalMapperImpl dtoMapper;
    //Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        Patient patient = dtoMapper.fromPatientDTO(patientDTO);
        log.info("Saving new patient");
        Patient savedPatient = patientRepository.save(patient);
        return dtoMapper.fromPatient(savedPatient);
    }

    @Override
    public RendezVous saveRendezVous(Date dateRendezVous, Long idPatient, String nomMedecin) throws PatientNotFoundException {
        Patient p = patientRepository.findById(idPatient).orElse(null);
        if(p==null)
            throw new PatientNotFoundException("Patient not found");
        Medecin m = medecinRepository.findByNomPr(nomMedecin);
        RendezVous rdv = new RendezVous();
        rdv.setDateRendezVous(dateRendezVous);
        rdv.setPatient(p);
        rdv.setMedecin(m);
        rdv.setStatus(RendezVousStatus.PENDING);

        RendezVous savedRdv = rendezVousRepository.save(rdv);

        return savedRdv;
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        Medecin m = medecinRepository.save(medecin);
        return m;
    }

    @Override
    public Consultation saveConsultation(Long idRdv, double prixConsultation, String rapportConsultation) throws RendezVousNotFoundException {
        RendezVous rdv = rendezVousRepository.findById(idRdv).orElse(null);
        if(rdv==null)
            throw new RendezVousNotFoundException("Rendez vous not found");
        Consultation c = new Consultation();
        c.setRapportConsultation(rapportConsultation);
        c.setRendezVous(rdv);
        c.setPrixConsultation(prixConsultation);
        Consultation savedConsultation = consultationRepository.save(c);
        return savedConsultation;
    }

    @Override
    public List<PatientDTO> listPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> patientDTOS = patients.stream().map(patient -> dtoMapper.fromPatient(patient)).collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<RendezVous> listRendezVous() {
        return rendezVousRepository.findAll();
    }

    @Override
    public List<RendezVous> rdvsPatient(Long idPatient) throws PatientNotFoundException {
        Patient p = patientRepository.findById(idPatient)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found!"));
        if(p==null)
            throw new PatientNotFoundException("Patient not found!");
        return rendezVousRepository.findByPatient(p);
    }

    @Override
    public Medecin searchMedecinByName(String name) throws MedecinNotFoundException {
        Medecin m = medecinRepository.findByNomPr(name);
        if(m==null)
            throw new MedecinNotFoundException("Medecin not found!");
        return m;
    }
    @Override
    public PatientDTO getPatient(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new PatientNotFoundException("Patient not found"));
        return dtoMapper.fromPatient(patient);
    }
    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) {
        Patient patient = dtoMapper.fromPatientDTO(patientDTO);
        log.info("Saving new patient");
        Patient savedPatient = patientRepository.save(patient);
        return dtoMapper.fromPatient(savedPatient);
    }
    @Override
    public void deletePatient(Long patientId){
        patientRepository.deleteById(patientId);
    }
}
