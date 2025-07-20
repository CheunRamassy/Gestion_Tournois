package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.GameService;
import com.dreams.gestiontournois.Service.SimpleEliminationService;
import com.dreams.gestiontournois.Service.UserService;
import com.dreams.gestiontournois.model.Game;
import com.dreams.gestiontournois.model.SimpleElimination;
import com.dreams.gestiontournois.model.Users;
import com.dreams.gestiontournois.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/SimpleElimination")
public class SimpleEliminationController {

    private final SimpleEliminationService simpleEliminationService;
    private final GameService gameService;
    private final UserService userService;

    public SimpleEliminationController(SimpleEliminationService simpleEliminationService, GameService gameService,
                                       UserService userService) {
        this.simpleEliminationService = simpleEliminationService;
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping("/ListeTournois")
    public String getAllSimpleElimination(Model model) {
        List<SimpleElimination> tournois = simpleEliminationService.getAllSimpleEliminations();
        model.addAttribute("tournois", tournois);
        return "/simpleElimination/index";
    }

    @GetMapping("/view/{id}")
    public String showSimpleElimination(@PathVariable("id") Long id, Model model) {
        Optional<SimpleElimination> tournoi =  simpleEliminationService.getSimpleEliminationById(id);
        model.addAttribute("tournoi", tournoi.get());
        return "/simpleElimination/view";
    }

    @GetMapping("/createTournoi")
    public String createSimpleElimination(Model model) {
        model.addAttribute("tournoi", new SimpleElimination());

        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        return "/simpleElimination/create";
    }

    @GetMapping("/editTournoi/{id}")
    public String editSimpleElimination(@PathVariable("id") Long id, Model model) {
        Optional<SimpleElimination> tournoi = simpleEliminationService.getSimpleEliminationById(id);
        model.addAttribute("tournoi", tournoi.get());
        return "/simpleElimination/create";
    }

    @PostMapping("/save")
    public String saveSimpleElimination(@ModelAttribute("tournoi") SimpleElimination tournoi,
                                        @RequestParam("jeuId") Long jeuId,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "simpleElimination/create";
        }

        Users currentUser = userService.getCurrentUser();
        tournoi.setUsers(currentUser);

        // Vérifie si le jeu préféré existe
        Optional<Game> selectedGame = gameService.getGameById(jeuId);
        selectedGame.ifPresent(tournoi::setGame);

        simpleEliminationService.saveSimpleElimination(tournoi);

        redirectAttributes.addFlashAttribute("success",
                tournoi.getId() != null ? "Tournoi mis à jour avec succès" : "Tournoi créé avec succès");
        return "redirect:/SimpleElimination/ListeTournois";
    }

    @PostMapping("/delete/{id}")
    public String deleteSimpleElimination(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean tournoi = simpleEliminationService.deleteSimpleEliminationById(id);
        if(tournoi) {
            redirectAttributes.addFlashAttribute("success", "Tournoi supprimé avec succès");
        } else {
            redirectAttributes.addFlashAttribute("error", "Tournoi non trouvé avec l'ID: " + id);
        } return "redirect:/SimpleElimination/ListeTournois";
    }
}
