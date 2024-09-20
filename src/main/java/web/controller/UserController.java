package web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/create-form")
    public String createUserForm(User user) {
        return "create-form";
    }

    @PostMapping("/create-form")
    public String createUser(User user) {
        service.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("username") String username,
                             @RequestParam("email") String email) {
        service.updateUser(id, username, email);
        return "redirect:/";
    }

    @GetMapping("/edit-form")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "edit-form";
    }

    @PostMapping("/edit-form")
    public String editUser(@RequestParam("id") Long id, User user) {
        service.updateUser(id, user.getUsername(), user.getEmail());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        service.deleteUser(id);
        return "redirect:/";
    }
}
