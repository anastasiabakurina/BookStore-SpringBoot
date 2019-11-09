package bookstore.controller;

import bookstore.model.User;
import bookstore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/loginPage")
    public String login(@Valid User user, BindingResult bindingResult, Model model) {
//        if (userRepo.findByUsername(user.getPassword()) == null) {
//            model.addAttribute("passwordError", "Password is not correct!");
//        }
//        if (user.getUsername() == null) {
//            model.addAttribute("usernameError", "Username is not correct!");
//        }
//
//        if ( bindingResult.hasErrors()) {
//            Map<String, String> errors = ControllerErrors.getErrors(bindingResult);
//            model.mergeAttributes(errors);
//            return "loginPage";
//        }
        return "loginPage";
    }
}
