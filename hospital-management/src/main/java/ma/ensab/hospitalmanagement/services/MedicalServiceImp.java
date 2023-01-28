package ma.ensab.hospitalmanagement.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ensab.hospitalmanagement.dtos.*;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
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
    public RendezVousDTO saveRendezVous(Date dateRendezVous, Long idPatient, String nomMedecin) throws PatientNotFoundException {
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

        return dtoMapper.fromRendezVous(savedRdv);
    }

    @Override
    public MedecinDTO saveMedecin(MedecinDTO medecinDTO) {

        Medecin m = dtoMapper.fromMedecinDTO(medecinDTO);
        Medecin savedMedecin = medecinRepository.save(m);
        return dtoMapper.fromMedecin(savedMedecin);
    }

    @Override
    public ConsultationDTO saveConsultation(Long idRdv, double prixConsultation, String rapportConsultation) throws RendezVousNotFoundException {
        RendezVous rdv = rendezVousRepository.findById(idRdv).orElse(null);
        if(rdv==null)
            throw new RendezVousNotFoundException("Rendez vous not found");
        Consultation c = new Consultation();
        c.setRapportConsultation(rapportConsultation);
        c.setRendezVous(rdv);
        c.setPrixConsultation(prixConsultation);
        Consultation savedConsultation = consultationRepository.save(c);
        return dtoMapper.fromConsultation(savedConsultation);
    }

    @Override
    public List<PatientDTO> listPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> patientDTOS = patients.stream().map(patient -> dtoMapper.fromPatient(patient)).collect(Collectors.toList());
        return patientDTOS;
    }

    @Override
    public List<RendezVousDTO> listRendezVous() {
        List<RendezVous> rdvs =  rendezVousRepository.findAll();
        List<RendezVousDTO> rdvDTOS = rdvs.stream().map(rdv -> dtoMapper.fromRendezVous(rdv)).collect(Collectors.toList());
        return rdvDTOS;
    }
    @Override
    public List<RendezVous> listRendezVouss(){
        return rendezVousRepository.findAll();
    }


    @Override
    public List<RendezVousDTO> rdvsPatient(Long idPatient) throws PatientNotFoundException {
        Patient p = patientRepository.findById(idPatient)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found!"));
        if(p==null)
            throw new PatientNotFoundException("Patient not found!");
        List<RendezVous> rdvs = rendezVousRepository.findByPatient(p);
        List<RendezVousDTO> rdvDTOS = rdvs.stream().map(rdv -> dtoMapper.fromRendezVous(rdv)).collect(Collectors.toList());
        return rdvDTOS;
    }


    @Override
    public MedecinDTO searchMedecinByName(String name) throws MedecinNotFoundException {
        Medecin m = medecinRepository.findByNomPr(name);
        if(m==null)
            throw new MedecinNotFoundException("Medecin not found!");
        return dtoMapper.fromMedecin(m);
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

    @Override
    public List<MedecinDTO> listMedecin() {
        List<Medecin> medecins = medecinRepository.findAll();
        List<MedecinDTO> medecinDTOs = medecins.stream().map(medecin -> dtoMapper.fromMedecin(medecin)).collect(Collectors.toList());
        return medecinDTOs;
    }

    @Override
    public PatientPageDTO listPatientspage(int page, int size) {
        Page<Patient> patients= patientRepository.getAllPatient(PageRequest.of(page, size));
        PatientPageDTO patientPageDTO = new PatientPageDTO();
        List<PatientDTO> patientDTOS = patients.getContent().stream().map(patient -> dtoMapper.fromPatient(patient)).collect(Collectors.toList());
        patientPageDTO.setPatientDTOS(patientDTOS);
        patientPageDTO.setCurrentPage(page);
        patientPageDTO.setPageSize(size);
        patientPageDTO.setTotalPages(patients.getTotalPages());
        return patientPageDTO;
    }

    @Override
    public PatientPageDTO searchPatientt(String keyword, int page, int size) {

        Page<Patient> patients= patientRepository.findByNomPrContaining(keyword,PageRequest.of(page, size));
        PatientPageDTO patientPageDTO = new PatientPageDTO();
        List<PatientDTO> patientDTOS = patients.getContent().stream().map(patient -> dtoMapper.fromPatient(patient)).collect(Collectors.toList());
       /* List<PatientDTO> newpatientDTOS = new ArrayList<>();
        for (PatientDTO p: patientDTOS){
            if(p.getNomPr().contains(keyword))
                newpatientDTOS.add(p);
        }*/

        patientPageDTO.setPatientDTOS(patientDTOS);
        patientPageDTO.setCurrentPage(page);
        patientPageDTO.setPageSize(size);
        patientPageDTO.setTotalPages(patients.getTotalPages());
        return patientPageDTO;
    }

    @Override
    public RendezVousPageDTO listofRendezvous(int page, int size) {
        /*Page<Patient> patients= patientRepository.getAllPatient(PageRequest.of(page, size));
        PatientPageDTO patientPageDTO = new PatientPageDTO();
        List<PatientDTO> patientDTOS = patients.getContent().stream().map(patient -> dtoMapper.fromPatient(patient)).collect(Collectors.toList());
        patientPageDTO.setPatientDTOS(patientDTOS);
        patientPageDTO.setCurrentPage(page);
        patientPageDTO.setPageSize(size);
        patientPageDTO.setTotalPages(patients.getTotalPages());
        return patientPageDTO;*/

        Page<RendezVous> rendezVouss = rendezVousRepository.getAllRendezVous(PageRequest.of(page, size));
        RendezVousPageDTO rendezVousPageDTO = new RendezVousPageDTO();
        List<RendezVousDTO> rendezVousDTOS = rendezVouss.getContent().stream().map(rdv -> dtoMapper.fromRendezVous(rdv)).collect(Collectors.toList());
        rendezVousPageDTO.setRendezVousDTOS(rendezVousDTOS);
        rendezVousPageDTO.setCurrentPage(page);
        rendezVousPageDTO.setPageSize(size);
        rendezVousPageDTO.setTotalPages(rendezVouss.getTotalPages());
        return rendezVousPageDTO;
    }

    @Override
    public RendezVousPageDTO searchRendezVousPatient(String keyword,int page, int size) {
        Page<RendezVous> rendezVouss = rendezVousRepository.getAllRendezPatient(keyword,PageRequest.of(page, size));
        RendezVousPageDTO rendezVousPageDTO = new RendezVousPageDTO();
        List<RendezVousDTO> rendezVousDTOS = rendezVouss.getContent().stream().map(rdv -> dtoMapper.fromRendezVous(rdv)).collect(Collectors.toList());
        rendezVousPageDTO.setRendezVousDTOS(rendezVousDTOS);
        rendezVousPageDTO.setCurrentPage(page);
        rendezVousPageDTO.setPageSize(size);
        rendezVousPageDTO.setTotalPages(rendezVouss.getTotalPages());
        return rendezVousPageDTO;
    }

    @Override
    public RendezVousPageDTO rdvPatient(Long idPatient, int page, int size) {
        Page<RendezVous> rendezVouss = rendezVousRepository.getAllRendezPatientId(idPatient,PageRequest.of(page, size));
        RendezVousPageDTO rendezVousPageDTO = new RendezVousPageDTO();
        List<RendezVousDTO> rendezVousDTOS = rendezVouss.getContent().stream().map(rdv -> dtoMapper.fromRendezVous(rdv)).collect(Collectors.toList());
        for (RendezVousDTO r: rendezVousDTOS){
            Consultation c = consultationRepository.getConsultationByIdRdv(r.getId());
            if(c!=null)
                r.setHasConsultation(true);
            else r.setHasConsultation(false);
        }
        rendezVousPageDTO.setRendezVousDTOS(rendezVousDTOS);
        rendezVousPageDTO.setCurrentPage(page);
        rendezVousPageDTO.setPageSize(size);
        rendezVousPageDTO.setTotalPages(rendezVouss.getTotalPages());
        return rendezVousPageDTO;
    }

    @Override
    public RendezVousDTO saveRendezVous(RendezVousDTO rendezVousDTO) {
        RendezVous rdv = dtoMapper.fromRendezVousDTO(rendezVousDTO);
        RendezVous savedRdv = rendezVousRepository.save(rdv);
        RendezVousDTO rdto = dtoMapper.fromRendezVous(savedRdv);
        rdto.setHasConsultation(false);
        //return dtoMapper.fromRendezVous(savedRdv);
        return rdto;
    }

    @Override
    public ConsultationDTO getConsultationByIdRendezVous(Long idRendezVous) {
        Consultation c = consultationRepository.getConsultationByIdRdv(idRendezVous);
        if(c==null) return null;
        return dtoMapper.fromConsultation(c);
    }


}
