package ca.sheridancollege.consmatt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
	
		http
		.authorizeRequests()
		.antMatchers("/employees").hasAnyRole("OWNER", "EMPLOYEE")
		.antMatchers("/addEmployee").hasRole("OWNER")
		.antMatchers("/delete/{id}").hasRole("OWNER")
		.antMatchers("/").permitAll() 
		.antMatchers("/h2-console/**").permitAll()
		.anyRequest().authenticated()
	.and()
		//Define Custom login
		.formLogin()
			.loginPage("/login")//URL defined in the SecurityController
			.permitAll()
	.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout")
			.permitAll()
	.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
			
	}
	@Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
