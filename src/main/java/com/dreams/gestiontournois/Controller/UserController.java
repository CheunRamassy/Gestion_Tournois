package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.UserService;
import com.dreams.gestiontournois.model.Game;
import com.dreams.gestiontournois.model.Users;
import com.dreams.gestiontournois.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/User")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/ListUsers")
    public String ListUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "User/index";
    }

    @GetMapping("/Registration")
    public String createAccount(Model model) {
        model.addAttribute("user", new Users());
        return "user/create";
    }

    @GetMapping("/Settings")
//    @PathVariable("id") Long id ,
    public String getUserService( Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        String username = userDetails.getUsername();
        Optional<Users> user = usersRepository.findBynomUtilisateur(username);
//        System.out.println(username);
//        System.out.println(user.get().getNomComplet());
//        if (user.isPresent()) {
            model.addAttribute("user", user.get());
//        } else {
//            model.addAttribute("error", "Utilisateur non trouvé");
//        };
        return "user/edit";
    }

    @PostMapping("/saveAccount")
    public String saveUserAccount(@ModelAttribute("user") Users users,
                                  BindingResult result,
                                  RedirectAttributes redirectAttribute) {

        if (result.hasErrors()) {
            return "user/create";
        }

        if (usersRepository.findBynomUtilisateur(users.getNomUtilisateur()).isPresent()) {
            redirectAttribute.addFlashAttribute("error", "Ce nom d'utilisateur est déjà utilisé");
            return "redirect:/UserInformation/Registration";
        }

        users.setPassword(passwordEncoder.encode(users.getPassword()));
//        users.setRole("ROLE_USER");

        userService.saveUsers(users, redirectAttribute);

        // Ajouter un message de succès
        redirectAttribute.addFlashAttribute("success",
                users.getId() != null ? "Jeu mis à jour avec succès" : "Jeu créé avec succès");
        return "redirect:/User/ListUsers"; // Thymeleaf retournera templates/login.html

    }

    @PostMapping("delete/{id}")
    public String deleteUsers(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean jeu = userService.deleteUserById(id);
        if (jeu) {
            redirectAttributes.addFlashAttribute("success", "Jeu supprimé avec succès");
        } else {
            redirectAttributes.addFlashAttribute("error", "Jeu non trouvé avec l'ID: " + id);
        }
        return "redirect:/User/ListUsers";
    }



//    @RequestMapping("/Settings")
//    public String getUserService(Model model) {
//        return "User/user";
//    }
}
