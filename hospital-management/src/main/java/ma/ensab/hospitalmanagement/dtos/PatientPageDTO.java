package ma.ensab.hospitalmanagement.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PatientPageDTO {
    private List<PatientDTO> patientDTOS;
    private int currentPage;
    private int totalPages;
    private int pageSize;

}
