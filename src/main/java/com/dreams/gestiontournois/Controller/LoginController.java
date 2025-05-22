package com.dreams.gestiontournois.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Connection")
public class LoginController {

    @GetMapping("/login")
    public String login() {
//        if (Authenticated()) {
//            return "redirect:userMainPage";
        return "login"; // Thymeleaf retournera templates/login.html
    }

    @GetMapping("/register")
    public String register() {
        return "login"; // Thymeleaf retournera templates/login.html
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout"; // Thymeleaf retournera templates/login.html
    }
}
