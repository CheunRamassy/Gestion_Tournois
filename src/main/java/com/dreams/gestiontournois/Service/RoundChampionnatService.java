package com.dreams.gestiontournois.Service;

import com.dreams.gestiontournois.model.RoundChampionnat;
import com.dreams.gestiontournois.repository.RoundChampionnatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoundChampionnatService {

    private RoundChampionnatRepository roundChampionnatRepository;

    @Autowired
    public RoundChampionnatService(RoundChampionnatRepository roundChampionnatRepository) {
        this.roundChampionnatRepository = roundChampionnatRepository;
    }

    public List<RoundChampionnat> getAllRoundChampionnat() {
        return roundChampionnatRepository.findAll();
    }

    public Optional<RoundChampionnat> getRoundChampionnatById(Long id) {
        return roundChampionnatRepository.findById(id);
    }

    public RoundChampionnat saveRoundChampionnat(RoundChampionnat roundChampionnat) {
        return roundChampionnatRepository.save(roundChampionnat);
    }

    public Boolean deleteRoundChampionnatById(Long id) {
        if(roundChampionnatRepository.existsById(id)) {
            roundChampionnatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
