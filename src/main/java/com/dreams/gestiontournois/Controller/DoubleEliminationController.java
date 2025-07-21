package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.DoubleEliminationService;
import com.dreams.gestiontournois.Service.GameService;
import com.dreams.gestiontournois.Service.UserService;
import com.dreams.gestiontournois.model.DoubleElimination;
import com.dreams.gestiontournois.model.Game;
import com.dreams.gestiontournois.model.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/DoubleElimination")
public class DoubleEliminationController {

    @Autowired
    private final DoubleEliminationService doubleEliminationService;
    private final GameService gameService;
    private final UserService userService;

    public DoubleEliminationController (DoubleEliminationService doubleEliminationService, GameService gameService,
                                        UserService userService){
        this.doubleEliminationService = doubleEliminationService;
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping("/ListeTournois")
    public String home (Model model) {
        List<DoubleElimination> tournois =  doubleEliminationService.getAllDoubleEliminations();
        model.addAttribute("tournois", tournois);
        return "doubleElimination/index";
    }

    @GetMapping("/view/{id}")
    public String showDoubleElimination (@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        Optional<DoubleElimination> tournois = doubleEliminationService.getDoubleElimination(id);


        String referer = request.getHeader("Referer");
        model.addAttribute("previousUrl", referer);
        model.addAttribute("tournois", tournois.get());
        return "doubleElimination/view";
    }

    @GetMapping("/createTournoi")
    public String createDoubleElimination (Model model) {
        model.addAttribute("tournoi", new DoubleElimination());

        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        return "doubleElimination/create";
    }

    @PostMapping("/save")
    public String saveUtilisateur(
            @ModelAttribute("tournois") DoubleElimination tournois,
            @RequestParam("jeuId") Long jeuId,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Vérifier s'il y a des erreurs de validation
        if (result.hasErrors()) {
            return "doubleElimination/create";
        }

        Users currentUser = userService.getCurrentUser();
        tournois.setUsers(currentUser);

        // Vérifie si le jeu préféré existe
        Optional<Game> selectedGame = gameService.getGameById(jeuId);
        selectedGame.ifPresent(tournois::setGame);

        // Sauvegarder l'utilisateur
        doubleEliminationService.saveDoubleElimination(tournois);

        // Ajouter un message de succèsé
        redirectAttributes.addFlashAttribute("success",
                tournois.getId() != null ? "Utilisateur mis à jour avec succès" : "Utilisateur créé avec succès");

        return "redirect:/DoubleElimination/ListeTournois";
    }

    @GetMapping("/editTournoi/{id}")
    public String updateDoubleElimination (@PathVariable("id") Long id, Model model) {
        Optional<DoubleElimination> tournois = doubleEliminationService.getDoubleElimination(id);
            model.addAttribute("tournoi", tournois.get());
            return "doubleElimination/create";

    }

    @PostMapping("/delete/{id}")
    public String deleteDoubleElimination (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean tournois = doubleEliminationService.deleteDoubleElimination(id);
        if (tournois){
            redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé avec succès");
        }
        else{
            redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé avec l'ID: " + id);
        }
        return "redirect:/DoubleElimination/ListeTournois";
    }

}
