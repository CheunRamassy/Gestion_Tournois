package com.dreams.gestiontournois.repository;


import com.dreams.gestiontournois.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
