package com.dreams.gestiontournois.repository;

import com.dreams.gestiontournois.model.DoubleElimination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path ="DoubleElimination")
public interface DoubleEliminationRepository extends JpaRepository<DoubleElimination, Long> {
}
