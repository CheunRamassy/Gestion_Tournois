package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.DoubleEliminationService;
import com.dreams.gestiontournois.model.DoubleElimination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class DoubleEliminationController {

    @Autowired
    private final DoubleEliminationService service;

    public DoubleEliminationController (DoubleEliminationService service){
        this.service = service;
    }

    @GetMapping("/")
    public String home (Model model) {
        List<DoubleElimination> tournois =  service.getAllDoubleEliminations();
        model.addAttribute("tournois", tournois);
        return "home";
    }

    @GetMapping("/view/{id}")
    public String showDoubleElimination (@PathVariable("id") Long id, Model model) {
        Optional<DoubleElimination> tournois = service.getDoubleElimination(id);
        model.addAttribute("tournois", tournois);
        return "detailTournois";
    }

    @PostMapping("/createTournois")
    public String createDoubleElimination (Model model) {
        model.addAttribute("tournois", new DoubleElimination());
        return "create";
    }

    @PutMapping("/editTournoi/{id}")
    public String updateDoubleElimination (Model model, Long id) {
        Optional<DoubleElimination> tournois = service.getDoubleElimination(id);
        model.addAttribute("tournois", tournois);
        return "create";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDoubleElimination (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Boolean tournois = service.deleteDoubleElimination(id);
        if (tournois){
            redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé avec succès");
        }
        else{
            redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé avec l'ID: " + id);
        }
        return "home";
    }

}
