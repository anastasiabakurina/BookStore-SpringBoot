package bookstore.controller;

import bookstore.config.ErrorsConfig;
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
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("passwordConfirmation") String passwordConfirmation,
                          @RequestParam("mail") String mail,
                          @Valid User user, BindingResult bindingResult, Model model) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirmation);
        boolean isEmailEmpty = StringUtils.isEmpty(mail);
        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmationError", "Password confirmation can not be empty!");
        }

        if (isEmailEmpty) {
            model.addAttribute("mailError", "Email can not be empty!");
        }

        if (user.getPassword() != null && !user.getPassword().equals(user.getPasswordConfirmation())) {
            model.addAttribute("passwordError", "Passwords are different!");
            return "registration";
        }

        if ( isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ErrorsConfig.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
        user.setActive(true);
        userRepo.save(user);
        return "redirect:/loginPage";
    }
}
