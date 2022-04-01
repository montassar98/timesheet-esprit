package tn.esprit.spring.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EntrepriseDto implements Serializable {
    private final int id;
    private final String name;
    private final String raisonSocial;
}
