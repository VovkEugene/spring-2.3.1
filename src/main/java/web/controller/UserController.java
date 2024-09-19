package web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/create")
    public String saveUser(@RequestParam("username") String username,
                           @RequestParam("email") String email) {
        User user = new User(username, email, Role.USER);
        service.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("username") String username,
                             @RequestParam("email") String email) {
        User user = service.getUserById(id);
        user.setUsername(username);
        user.setEmail(email);
        service.updateUser(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        service.deleteUser(id);
        return "redirect:/";
    }
}
