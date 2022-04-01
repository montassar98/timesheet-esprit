package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	Logger log  = Logger.getLogger(EntrepriseServiceImpl.class.getName());
	
	public int ajouterEntreprise(Entreprise entreprise) {
		log.debug("ajouterEntreprise");
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		log.debug("ajouterDepartement");
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		Optional<Entreprise> entrepriseDto = entrepriseRepoistory.findById(entrepriseId);
		Optional<Departement> departementDto = deptRepoistory.findById(depId);
		if (entrepriseDto.isPresent() && departementDto.isPresent()){
			Entreprise entrepriseManagedEntity = entrepriseDto.get();
			Departement depManagedEntity = departementDto.get();
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);

		}



	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> entrepriseDto = entrepriseRepoistory.findById(entrepriseId);
		if (entrepriseDto.isPresent()) {
			Entreprise entrepriseManagedEntity = entrepriseDto.get();
			List<String> depNames = new ArrayList<>();
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			return depNames;
		}else return new ArrayList<>();
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional<Entreprise> departementDto = entrepriseRepoistory.findById(entrepriseId);
		departementDto.ifPresent(dto -> entrepriseRepoistory.delete(dto));
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional<Departement> departementDto = deptRepoistory.findById(depId);
		departementDto.ifPresent(dto -> deptRepoistory.delete(dto));
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional<Entreprise> entrepriseDto = entrepriseRepoistory.findById(entrepriseId);
		return entrepriseDto.orElse(null);
	}

}
