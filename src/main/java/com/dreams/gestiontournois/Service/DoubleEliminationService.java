package com.dreams.gestiontournois.Service;

import com.dreams.gestiontournois.model.DoubleElimination;
import com.dreams.gestiontournois.repository.DoubleEliminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoubleEliminationService {

    private final DoubleEliminationRepository doubleEliminationRepository;

    @Autowired
    public DoubleEliminationService(DoubleEliminationRepository doubleEliminationRepository) {
        this.doubleEliminationRepository = doubleEliminationRepository;
    }

//    Récupèrer tous les tournois
    public List<DoubleElimination> getAllDoubleEliminations() {
        return doubleEliminationRepository.findAll();
    }

    public Optional<DoubleElimination> getDoubleElimination(Long id) {
        return doubleEliminationRepository.findById(id);
    }

    public DoubleElimination saveDoubleElimination(DoubleElimination doubleElimination) {
        return doubleEliminationRepository.save(doubleElimination);
    }

    public boolean deleteDoubleElimination(Long id) {
        if (doubleEliminationRepository.existsById(id)) {
            doubleEliminationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
