package com.dreams.gestiontournois.repository;

import com.dreams.gestiontournois.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findBynomUtilisateur(String nomUtilisateur);
}
