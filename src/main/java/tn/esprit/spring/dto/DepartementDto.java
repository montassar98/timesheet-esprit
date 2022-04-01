package tn.esprit.spring.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DepartementDto implements Serializable {
    private final int id;
    private final String name;
    private final List<EmployeDto> employes;
    private final List<MissionDto> missions;
    private final EntrepriseDto entreprise;
}
