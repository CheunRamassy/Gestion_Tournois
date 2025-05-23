package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.SimpleEliminationService;
import com.dreams.gestiontournois.model.SimpleElimination;
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

    public SimpleEliminationController(SimpleEliminationService simpleEliminationService) {
        this.simpleEliminationService = simpleEliminationService;
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
        return "/simpleElimination/create";
    }

    @GetMapping("/editTournoi/{id}")
    public String editSimpleElimination(@PathVariable("id") Long id, Model model) {
        Optional<SimpleElimination> tournoi = simpleEliminationService.getSimpleEliminationById(id);
        model.addAttribute("simpleElimination", simpleEliminationService.getSimpleEliminationById(id));
        return "/simpleElimination/create";
    }

    @PostMapping("/save")
    public String saveSimpleElimination(@ModelAttribute("tournoi") SimpleElimination tournoi,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "roundChampionnat/create";
        }

        simpleEliminationService.saveSimpleElimination(tournoi);

        redirectAttributes.addFlashAttribute("success",
                tournoi.getId() != null ? "Utilisateur mis à jour avec succès" : "Utilisateur créé avec succès");
        return "redirect:/SimpleElimination/ListeTournois";
    }

    @PostMapping("/delete/{id}")
    public String deleteSimpleElimination(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean tournoi = simpleEliminationService.deleteSimpleEliminationById(id);
        if(tournoi) {
            redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé avec succès");
        } else {
            redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé avec l'ID: " + id);
        } return "redirect:/SimpleElimination/ListeTournois";
    }

}
