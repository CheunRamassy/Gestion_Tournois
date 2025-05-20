package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.model.RoundChampionnat;
import com.dreams.gestiontournois.repository.RoundChampionnatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RoundRobin")
public class RoundChampionnatController {

    RoundChampionnatRepository roundChampionnatRepository;

    @GetMapping("/")
    public List<RoundChampionnat> getAllRoundChampionnat() {
        return (List<RoundChampionnat>) roundChampionnatRepository.findAll();
    }

    @PostMapping("/")
    public RoundChampionnat createRoundChampionnat(@RequestBody RoundChampionnat roundChampionnat) {
        roundChampionnatRepository.save(roundChampionnat);
        return roundChampionnat;
    }
}
