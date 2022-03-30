package tn.esprit.spring;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.EntrepriseDto;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@SpringBootTest
public class TestEntrepriseServiceImpl {

    Logger log = Logger.getLogger(TestEntrepriseServiceImpl.class.getName());
    @Autowired
    EntrepriseRepository entrepriseRepoistory;
    @Autowired
    DepartementRepository deptRepoistory;

    @Test
    public void testAjouterEntreprise(EntrepriseDto entreprise){
        log.debug("testAjouterEntreprise");
        entrepriseRepoistory.save(entreprise);
    }
}
