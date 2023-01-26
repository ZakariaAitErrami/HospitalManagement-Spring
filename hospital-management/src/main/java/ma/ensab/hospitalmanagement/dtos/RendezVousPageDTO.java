package ma.ensab.hospitalmanagement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RendezVousPageDTO {
    private List<RendezVousDTO> rendezVousDTOS;
    private int currentPage;
    private int totalPages;
    private int pageSize;

}
