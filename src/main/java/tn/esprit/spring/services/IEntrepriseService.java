package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.DepartementDto;
import tn.esprit.spring.entities.EntrepriseDto;

public interface IEntrepriseService {
	
	public int ajouterEntreprise(EntrepriseDto entreprise);
	public int ajouterDepartement(DepartementDto dep);
	void affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public void deleteEntrepriseById(int entrepriseId);
	public void deleteDepartementById(int depId);
	public EntrepriseDto getEntrepriseById(int entrepriseId);
}
