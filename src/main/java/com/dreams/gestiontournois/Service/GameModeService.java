package com.dreams.gestiontournois.Service;

import com.dreams.gestiontournois.model.GameMode;
import com.dreams.gestiontournois.repository.GameModeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameModeService {

    private final GameModeRepository gameModeRepository;

    public GameModeService(GameModeRepository gameModeRepository) {
        this.gameModeRepository = gameModeRepository;
    }

    public List<GameMode> getAllGameModes() {
        return gameModeRepository.findAll();
    }

    public Optional<GameMode> getGameModeById(Long id) {
        return gameModeRepository.findById(id);
    }

    public GameMode saveGameMode(GameMode gameMode) {
        return gameModeRepository.save(gameMode);
    }
    public Boolean deleteGameMode(Long id) {
        if(gameModeRepository.existsById(id)) {
            gameModeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
