package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.GameService;
import com.dreams.gestiontournois.Service.UserService;
import com.dreams.gestiontournois.model.Game;
import com.dreams.gestiontournois.model.Users;
import com.dreams.gestiontournois.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final UserService UsersService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GameService gameService;

    public UserController(UserService UsersService, PasswordEncoder passwordEncoder,
                          UserRepository userRepository, GameService gameService) {
        this.UsersService = UsersService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.gameService = gameService;
    }

    @GetMapping("/ListUsers")
    public String ListUsers(Model model) {
        List<Users> users = UsersService.getAllUsers();
        model.addAttribute("users", users);
        return "User/index";
    }

    @GetMapping("/Registration")
    public String createAccount(Model model) {
        model.addAttribute("user", new Users());

        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        return "user/create";
    }

    @GetMapping("/Settings")
    public String getUserService( Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        String username = userDetails.getUsername();
        Optional<Users> user = userRepository.findBynomUtilisateur(username);
        model.addAttribute("user", user.get());
        return "user/edit";
    }

    @PostMapping("/saveAccount")
    public String saveUserAccount(@ModelAttribute("user") Users users,
                                  @RequestParam("jeuPrefereId") Long jeuId,
                                  BindingResult result,
                                  RedirectAttributes redirectAttribute) {

        if (result.hasErrors()) {
            return "user/create";
        }

        // Vérifie si le jeu préféré existe
        Optional<Game> selectedGame = gameService.getGameById(jeuId);
        selectedGame.ifPresent(users::setJeuPrefere);

        if (userRepository.findBynomUtilisateur(users.getNomUtilisateur()).isPresent()) {
            redirectAttribute.addFlashAttribute("error", "Ce nom d'utilisateur est déjà utilisé");
            return "redirect:/UserInformation/Registration";
        }

        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole("ROLE_USER");


        UsersService.saveUsers(users);

        // Ajouter un message de succès
        redirectAttribute.addFlashAttribute("success",
                users.getId() != null ? "Jeu mis à jour avec succès" : "Jeu créé avec succès");
        return "redirect:/login"; // Thymeleaf retournera templates/login.html

    }

    @GetMapping("/InformationPlayer")
    public String showInformationUsers(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        String username = userDetails.getUsername();
        Optional<Users> player = userRepository.findBynomUtilisateur(username);
        model.addAttribute("player", player.get());
        return "player/view";
    }

    @PostMapping("/delete/{id}")
    public String deleteUsers(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean jeu = UsersService.deleteUserById(id);
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
