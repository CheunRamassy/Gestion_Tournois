package com.dreams.gestiontournois.Controller;

import com.dreams.gestiontournois.Service.UserService;
import com.dreams.gestiontournois.model.User;
import com.dreams.gestiontournois.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/UserInformation")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/Settings/{id}")
    public String getUserService(@PathVariable("id") Long id , Model model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user.get());
        return "/User/user";
    }

    @RequestMapping("/Settings")
    public String getUserService(Model model) {
        return "User/user";
    }
}
