package com.dreams.gestiontournois.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/Home")
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
