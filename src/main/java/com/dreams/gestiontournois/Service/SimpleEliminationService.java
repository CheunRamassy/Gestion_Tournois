package com.dreams.gestiontournois.Service;

import com.dreams.gestiontournois.model.SimpleElimination;
import com.dreams.gestiontournois.repository.SimpleEliminationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleEliminationService {

    SimpleEliminationRepository simpleEliminationRepository;

    public SimpleEliminationService(SimpleEliminationRepository simpleEliminationRepository) {
        this.simpleEliminationRepository = simpleEliminationRepository;
    }

    public List<SimpleElimination> getAllSimpleEliminations(){
        return simpleEliminationRepository.findAll();
    }

    public Optional<SimpleElimination> getSimpleEliminationById(Long id){
        return simpleEliminationRepository.findById(id);
    }

    public Boolean deleteSimpleEliminationById(Long id){
        if(simpleEliminationRepository.existsById(id)){
            simpleEliminationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public SimpleElimination saveSimpleElimination(SimpleElimination simpleElimination){
        return simpleEliminationRepository.save(simpleElimination);
    }
}
