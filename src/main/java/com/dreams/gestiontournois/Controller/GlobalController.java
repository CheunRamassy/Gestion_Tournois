package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.UserService;
import com.dreams.gestiontournois.model.Users;
import com.dreams.gestiontournois.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("currentUser")
    public String getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }
}
