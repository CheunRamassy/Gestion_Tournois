package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.RoundChampionnatService;
import com.dreams.gestiontournois.model.RoundChampionnat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/RoundRobin")
public class RoundChampionnatController {

    @Autowired
    private final RoundChampionnatService roundChampionnatService;

    public RoundChampionnatController(RoundChampionnatService roundChampionnatService) {
        this.roundChampionnatService = roundChampionnatService;
    }

    @GetMapping("/ListeTournois")
    public String getAllRoundChampionnat(Model model) {
       List<RoundChampionnat> tournois =  roundChampionnatService.getAllRoundChampionnat();
       model.addAttribute("tournois", tournois);
        return "roundChampionnat/index";
    }

    @GetMapping("/view/{id}")
    public String showRoundChampionnat(@PathVariable("id") Long id, Model model) {
        Optional<RoundChampionnat> tournoi = roundChampionnatService.getRoundChampionnatById(id);
        model.addAttribute("tournoi", tournoi);
        return "roundChampionnat/view";
    }

    @GetMapping("/createTournoi")
    public String createRoundChampionnat(Model model) {
        model.addAttribute("tournoi", new RoundChampionnat());
        return "roundChampionnat/create";
        }

    @GetMapping("/save")
    public String saveRoundChampionnat(
            @ModelAttribute("tournoi") RoundChampionnat tournoi,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "roundChampionnat/create";
        }

        redirectAttributes.addFlashAttribute("success",
                tournoi.getId() != null ? "Utilisateur mis à jour avec succès" : "Utilisateur créé avec succès");
        return "redirect:roundChampionnat/ListeTournois/";
    }

    @GetMapping("/editTournoi/{id}")
    public String editRoundChampionnat(@PathVariable("id") Long id, Model model) {
        Optional<RoundChampionnat> tournoi = roundChampionnatService.getRoundChampionnatById(id);
        model.addAttribute("tournoi", tournoi);
        return "roundChampionnat/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoundChampionnat(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean tournoi = roundChampionnatService.deleteRoundChampionnatById(id);
        if (tournoi) {
            redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé avec succès");
        } else {
            redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé avec l'ID: " + id);
        }
        return "redirect:roundChampionnat/ListeTournois/";
    }
}
