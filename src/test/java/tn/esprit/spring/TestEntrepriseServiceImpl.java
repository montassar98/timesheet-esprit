package tn.esprit.spring;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
public class TestEntrepriseServiceImpl {

    Logger log = Logger.getLogger(TestEntrepriseServiceImpl.class.getName());
    @Autowired
    EntrepriseRepository entrepriseRepoistory;
    @Autowired
    DepartementRepository deptRepoistory;

    @Test
    public void testCreateDepartment() {
        log.debug("testCreateDepartment");
        Departement dep = new  Departement("test departement");
        assertNotNull(dep);
    }

    @Test
    public void testCreateEntreprise() {
        log.debug("testCreateEntreprise");
        Entreprise entreprise = new Entreprise("test entrepise","test raison social");
        assertNotNull(entreprise);
    }


    @Test
    public void testAjouterEntreprise(){
        log.debug("testAjouterEntreprise");
        Entreprise entreprise = new Entreprise("test name","raison test");
        entrepriseRepoistory.save(entreprise);
        Optional<Entreprise> optionalEntreprise = entrepriseRepoistory.findById(entreprise.getId());
        assertNotNull(optionalEntreprise.get());
    }

    @Test
    public void testAjouterDepartement() {
        log.debug("test AjouterDepartement method");
        Departement dep = new  Departement("test departement");
        assertNotNull(dep);
        //deptRepoistory.save(dep);
        //assertNotNull(dep.getId());
    }
}
