package ca.sheridancollege.consmatt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.consmatt.beans.User;
import ca.sheridancollege.consmatt.repositories.UserRepository;

@Controller
public class SecurityController {

	@Autowired 
	@Lazy
	private UserRepository userRepo;
	
	@GetMapping("/login")
	public String goLogin() {  //This brings you to the login page
		return "login.html";
	}
	
	@GetMapping("/access-denied")
	public String accessdenied() {  //This method will bring you to the access denied page 
		return "access-denied.html";
	}
	
	@GetMapping("/registerUser")
	public String getToRegistration() { //This page is where you can register a user
		return "addemployee.html";
	}
	
	@PostMapping("/registerUser")
	public String collectRegistrationData(@RequestParam String username,            //This is the postmapping to submit the user
											@RequestParam String password) {
		userRepo.addUser(username, password);
		User user = userRepo.findUserAccount(username);
		userRepo.addRole(user.getUserId(), 1); //OWNER        //These add the roles to the user.
		userRepo.addRole(user.getUserId(), 2); //EMPLOYEE

		return "redirect:/root.html";
	}
	
	
	
}
