package tn.esprit.spring.dto;

import lombok.Data;
import tn.esprit.spring.entities.Role;

import java.io.Serializable;

@Data
public class EmployeDto implements Serializable {
    private final int id;
    private final String prenom;
    private final String nom;
    private final String email;
    private final String password;
    private final boolean actif;
    private final Role role;
}
