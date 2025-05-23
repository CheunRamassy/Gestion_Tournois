package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.GameModeService;
import com.dreams.gestiontournois.model.GameMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class GameModeController {

    private final GameModeService gameModeService;

    public GameModeController(GameModeService gameModeService) {
        this.gameModeService = gameModeService;
    }

    @GetMapping("/ListeGameMode")
    public String ListeGameMode(Model model) {
        List<GameMode> gameModes = gameModeService.getAllGameModes();
        model.addAttribute("gameModes", gameModes);
        return "index";
    }

    @GetMapping("/view/{id}")
    public String viewGameMode(@PathVariable("id") Long id, Model model) {
        Optional<GameMode> gameMode = gameModeService.getGameModeById(id);
        model.addAttribute("gameMode", gameMode.get());
        return "view";
    }

    @GetMapping("/create")
    public String createGameMode(Model model) {
        model.addAttribute("gameMode", new GameMode());
        return "create";
    }

    @GetMapping("/editGameMode/{id}")
    public String editGameMode(@PathVariable("id") Long id, Model model) {
        Optional<GameMode> gameMode = gameModeService.getGameModeById(id);
        model.addAttribute("gameMode", gameMode.get());
        return "edit";
    }

    @PostMapping("/save")
    public String saveGameMode(@ModelAttribute("gameMode") GameMode gameMode,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "create";
        }

        gameModeService.saveGameMode(gameMode);

        redirectAttributes.addFlashAttribute("success",
                gameMode.getId() != null ? "Utilisateur mis à jour avec succès" : "Utilisateur créé avec succès");
        return "redirect:/ListeGameMode";
    }

    @PostMapping("/delete/{id}")
    public String deleteGameMode(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean gameMode = gameModeService.deleteGameMode(id);
        if (gameMode) {
            redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé avec succès");
        } else {
            redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé avec l'ID: " + id);
        }
        return "redirect:/ListeGameMode";
        }
}
