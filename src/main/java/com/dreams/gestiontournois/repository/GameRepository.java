package com.dreams.gestiontournois.repository;

import com.dreams.gestiontournois.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}