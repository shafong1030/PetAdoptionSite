package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.service.userService;

@Configuration
@EnableWebSecurity
public class securityConfiguration{
	
	@Autowired
	private userService us;
	
	//SecurityFilterChain: Allow all htp request for login, new reg, javascript css file and img file, for other requests, need to be authenticated
	//login with degsinated login page
	//make the http session invlid, clear authen, logoutUrl, redirect after logout
	//disable csrf
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.userDetailsService(us)
	     .authorizeHttpRequests((authz) -> authz.requestMatchers("login/**","/NewReg**","/js/**","/css/**","/img/**","/websocket/**","/topic/**").permitAll()
	    		                                .anyRequest().authenticated())
	     .formLogin((authz) -> authz.loginPage("/login"))
	     .logout((authz)->authz.invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll())
	     .csrf((csrf) -> csrf.disable());
	     return http.build();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(us);
		auth.setPasswordEncoder(passwordEncoder());	
		return auth;
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager() {
	        return new ProviderManager(authenticationProvider());
	 }
	
	

}
