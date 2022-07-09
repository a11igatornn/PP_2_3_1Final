package webhib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webhib.model.User;
import webhib.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/user-createform")
    public String newUserForm (@ModelAttribute("user") User user) {
    return "new";
    }
    @PostMapping("/user-createform")
    public String newUserCreate (@ModelAttribute("user") User user) {
        userService.createOrUpdateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String editForm (Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.readUser(id));
        return "edit";
    }
    @PatchMapping("/user-update/{id}")
    public String editUser (@ModelAttribute("user") User user) {
        userService.createOrUpdateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping ("/user-delete/{id}")
    public String deleteUser (@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
