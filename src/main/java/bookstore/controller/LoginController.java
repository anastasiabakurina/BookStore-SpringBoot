package bookstore.controller;

import bookstore.model.User;
import bookstore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @PostMapping("/loginPage")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password, @Valid User user, Model model) {
        if (username != null && username.equals(user.getUsername())
                && password != null && password.equals(user.getPassword())) {
            return "redirect:/booksUser";
        }
        return "loginPage";
    }
}
