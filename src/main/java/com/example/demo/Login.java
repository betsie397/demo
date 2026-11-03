package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;

@Controller 
public class Login {

	@GetMapping({"/", "/login"})
	public String login(Model model) throws IOException{
		return "login";
	}

    @PostMapping({"/", "/login"})
    public String loggedIn(Model model) throws IOException{
		return new HomePage().home(model);
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) throws IOException{
        return "signup";
    }

    @PostMapping("/sign-up")
    public String signedUp(Model model) throws IOException{
        return new HomePage().home(model);
    }
}