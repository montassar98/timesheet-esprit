package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.DepartementDto;

@Repository
public interface DepartementRepository extends CrudRepository<DepartementDto, Integer>{

}
