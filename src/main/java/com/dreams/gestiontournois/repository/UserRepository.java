package com.dreams.gestiontournois.repository;

import com.dreams.gestiontournois.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
