package webhib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import webhib.model.User;
import webhib.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/users") //название ссылки
    public String findAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list"; //имя страницы
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("user-create")
    public String createUser(User user) {
    userService.createOrUpdateUser(user);
    return "redirect:/users";
    }
    @GetMapping("/user-delete/{id}")
    public String deleteUser (@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
@GetMapping("user-update/{id}")
    public String updateUserForm (@PathVariable("id") long id, Model model) {
        User user = userService.readUser(id);
        model.addAttribute("user", user);
        return "user-update";

    }
@PostMapping("/user-update")
    public String updateUser (User user) {
        userService.createOrUpdateUser(user);
        return "redirect:/users";
    }

}
