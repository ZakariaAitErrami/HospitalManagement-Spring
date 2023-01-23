package ma.ensab.hospitalmanagement.dtos;


import lombok.Data;

import java.util.Date;


@Data
public class PatientDTO {

    private Long id;
    private String nomPr;
    private Date dateNaissance;
    private String phoneNumber;

}


