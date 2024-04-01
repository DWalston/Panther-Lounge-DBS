package com.panther.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/adminBase", "/admin/**").hasRole("ADMIN")
			.requestMatchers("/checkout", "/advanced", "/checkin").hasRole("USER")
				.requestMatchers("/", "/index","/static/**", "/catalog/**", "/css/**", "/js/**", "/register").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/", true)
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails viewer =
			 User.withDefaultPasswordEncoder()
				.username("none")
				.password("test")
				.build();
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("dummy")
				.password("test")
				.roles("USER")
				.build();
		UserDetails admin =
			 User.withDefaultPasswordEncoder()
				.username("admin")
				.password("420")
				.roles("USER","ADMIN")
				.build();

		return new InMemoryUserDetailsManager(viewer, user, admin);
	}
}
