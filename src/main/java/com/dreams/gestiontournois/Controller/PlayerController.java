package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.PlayerService;
import com.dreams.gestiontournois.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/Player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping("/Information/{id}")
    public String showInformationPlayer(@PathVariable("id") Long id, Model model) {
        Optional<Player> player = playerService.getPlayerById(id);
        model.addAttribute("player", player.get());
        return "Player/view";
    }

    @RequestMapping("/Information")
    public String showInformationPlayer( Model model) {
        return "Player/view";
    }

}
