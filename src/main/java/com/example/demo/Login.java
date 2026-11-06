package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@Controller 
public class Login{
    private final UserRepository userRepository;

    public Login(UserRepository userRepository){
        this.userRepository = userRepository;
    }

	@GetMapping({"/", "/login"})
	public String login(Model model, HttpSession session) throws IOException{
        session.removeAttribute("userId");

		return "login";
	}

    @PostMapping({"/", "/login"})
    public String loggedIn(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) throws IOException{
        User user = userRepository.findByEmail(email).orElse(null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        boolean valid = encoder.matches(password, user.getPasswordHash());
        if (valid) { 
            session.setAttribute("userId", user.getId());
            return "redirect:/home"; 
        }

		return login(model, session);
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) throws IOException{
        return "signup";
    }

    @PostMapping("/sign-up")
    public String signedUp(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model, HttpSession session) throws IOException{
        if (userRepository.findByEmail(email).isPresent()){
            return "redirect:/login";
        }
        if (!password.equals(confirmPassword)){
            return "redirect:/sign-up";
        }

        String hash = new BCryptPasswordEncoder().encode(password);
        User user = new User(name, email, hash);

        User savedUser = userRepository.save(user);
        session.setAttribute("userId", savedUser.getId());
        return "redirect:/settings";
    }
}