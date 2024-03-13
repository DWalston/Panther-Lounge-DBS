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
		http.authenticationProvider(authenticationProvider());
		http
			.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
			.requestMatchers("/checkout", "/advanced", "/checkin").hasRole("USER")
				.requestMatchers("/", "/index","/static/**").permitAll()
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
	Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
	@Bean
	public UserDetailsService userDetailsService() {
/*		UserDetails viewer =
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
				.build();*/

		return new MemberUserDetailsService();
	}
}
