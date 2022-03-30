package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.TimesheetApplication;
import tn.esprit.spring.entities.DepartementDto;
import tn.esprit.spring.entities.EntrepriseDto;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	Logger log  = Logger.getLogger(EntrepriseServiceImpl.class.getName());
	
	public int ajouterEntreprise(EntrepriseDto entreprise) {
		log.debug("ajouterEntreprise");
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(DepartementDto dep) {
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
		Optional<EntrepriseDto> entrepriseDto = entrepriseRepoistory.findById(entrepriseId);
		Optional<DepartementDto> departementDto = deptRepoistory.findById(depId);
		if (entrepriseDto.isPresent() && departementDto.isPresent()){
			EntrepriseDto entrepriseManagedEntity = entrepriseDto.get();
			DepartementDto depManagedEntity = departementDto.get();
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);

		}



	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<EntrepriseDto> entrepriseDto = entrepriseRepoistory.findById(entrepriseId);
		if (entrepriseDto.isPresent()) {
			EntrepriseDto entrepriseManagedEntity = entrepriseDto.get();
			List<String> depNames = new ArrayList<>();
			for(DepartementDto dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			return depNames;
		}else return new ArrayList<>();
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional<EntrepriseDto> departementDto = entrepriseRepoistory.findById(entrepriseId);
		departementDto.ifPresent(dto -> entrepriseRepoistory.delete(dto));
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional<DepartementDto> departementDto = deptRepoistory.findById(depId);
		departementDto.ifPresent(dto -> deptRepoistory.delete(dto));
	}


	public EntrepriseDto getEntrepriseById(int entrepriseId) {
		Optional<EntrepriseDto> entrepriseDto = entrepriseRepoistory.findById(entrepriseId);
		return entrepriseDto.orElse(null);
	}

}
