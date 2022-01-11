package ca.sheridancollege.consmatt.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.consmatt.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

	@Autowired
	@Lazy
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Find the user based on their user name
		ca.sheridancollege.consmatt.beans.User user = userRepo.findUserAccount(username);
		
		//If the user doesn't exist then throw and exception
		if (user == null) {
			System.out.println("User not found "+ username);
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		//Get the roles of the user
		List<String> roles = userRepo.getRolesById(user.getUserId());
		System.out.println(roles);		//Change the user roles into a list of GrantedAuthority
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roles != null) {
			for(String role : roles ) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
				
		}
		
		//Create a Spring User based on the information collected
		User springUser = new User(user.getUserName(), user.getEncryptedPassword(), grantList);
		
		//Return the Spring user
		UserDetails userDetails = (UserDetails)springUser;
		return userDetails;
	}
	
	
	
}
