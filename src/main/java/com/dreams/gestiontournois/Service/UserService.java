package com.dreams.gestiontournois.Service;

import com.dreams.gestiontournois.model.Game;
import com.dreams.gestiontournois.model.Users;
import com.dreams.gestiontournois.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class UserService implements UserDetailsService{

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomUtilisateur) throws UsernameNotFoundException {
        Users users = usersRepository.findBynomUtilisateur(nomUtilisateur)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(users.getNomUtilisateur())
                .password(users.getPassword())
                .authorities("ROLE_USER")
                .build();
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public Users saveUsers(Users users, RedirectAttributes redirectAttribute) {
        if (usersRepository.findBynomUtilisateur(users.getNomUtilisateur()).isPresent()) {
            redirectAttribute.addFlashAttribute("error", "L'utilisateur existe!");
        }
        return usersRepository.save(users);
    }

    public Boolean deleteUserById(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
