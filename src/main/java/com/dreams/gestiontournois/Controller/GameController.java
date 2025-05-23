package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.GameService;
import com.dreams.gestiontournois.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Jeux")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/ListeJeux")
    public String ListeJeux(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "Game/index";
    }

    @GetMapping("/view/{id}")
    public String viewGame(@PathVariable("id") Long id, Model model) {
        Optional<Game> game = gameService.getGameById(id);
        model.addAttribute("game", game.get());
        return "Game/view";
    }

    @GetMapping("/create")
    public String createGame(Model model) {
        model.addAttribute("game", new Game());
        return "Game/create";
    }

    @GetMapping("/editJeu/{id}")
    public String editGame(@PathVariable("id") Long id, Model model) {
        Optional<Game> game = gameService.getGameById(id);
        model.addAttribute("game", game.get());
        return "Game/create";
    }

    @PostMapping("/save")
    public String saveGame(@ModelAttribute("game") Game game,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Game/create";
        }

        gameService.saveGame(game);

        redirectAttributes.addFlashAttribute("success",
                game.getId() != null ? "Utilisateur mis à jour avec succès" : "Utilisateur créé avec succès");
        return "redirect:/Jeux/ListeJeux";
    }

    @PostMapping("delete/{id}")
    public String deleteGame(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean jeu = gameService.deleteGameById(id);
        if (jeu) {
        redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé avec succès");
    } else {
        redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé avec l'ID: " + id);
    }
        return "redirect:/Jeux/ListeJeux";
    }
}
