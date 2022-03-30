package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.DepartementDto;
import tn.esprit.spring.entities.EntrepriseDto;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@Controller
public class ControllerEntrepriseImpl {

    @Autowired
    IEmployeService iemployeservice;
    @Autowired
    IEntrepriseService ientrepriseservice;
    @Autowired
    ITimesheetService itimesheetservice;

    public int ajouterEntreprise(EntrepriseDto ssiiConsulting) {
        ientrepriseservice.ajouterEntreprise(ssiiConsulting);
        return ssiiConsulting.getId();
    }

    public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
        ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
    }

    public void deleteEntrepriseById(int entrepriseId) {
        ientrepriseservice.deleteEntrepriseById(entrepriseId);
    }

    public EntrepriseDto getEntrepriseById(int entrepriseId) {

        return ientrepriseservice.getEntrepriseById(entrepriseId);
    }

    public int ajouterDepartement(DepartementDto dep) {
        return ientrepriseservice.ajouterDepartement(dep);
    }

    public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
        return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
    }

    public void deleteDepartementById(int depId) {
        ientrepriseservice.deleteDepartementById(depId);

    }
}
