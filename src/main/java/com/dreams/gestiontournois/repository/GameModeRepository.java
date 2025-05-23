package com.dreams.gestiontournois.repository;

import com.dreams.gestiontournois.model.GameMode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameModeRepository extends JpaRepository<GameMode, Long> {
}