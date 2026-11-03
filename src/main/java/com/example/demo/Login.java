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
import java.io.IOException;

@Controller 
public class Login{
    private final UserRepository userRepository;

    public Login(UserRepository userRepository){
        this.userRepository = userRepository;
    }

	@GetMapping({"/", "/login"})
	public String login(Model model) throws IOException{
		return "login";
	}

    @PostMapping({"/", "/login"})
    public String loggedIn(@RequestParam String email, @RequestParam String password, Model model) throws IOException{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        boolean valid = userRepository.findByEmail(email).map(user -> encoder.matches(password, user.getPasswordHash())).orElse(false);
        if (valid) { return "redirect:/home"; }

		return login(model);
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) throws IOException{
        return "signup";
    }

    @PostMapping("/sign-up")
    public String signedUp(@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model) throws IOException{
        String hash = new BCryptPasswordEncoder().encode(password);

        userRepository.save(new User(name, email, hash));
        return "redirect:/home";
    }
}