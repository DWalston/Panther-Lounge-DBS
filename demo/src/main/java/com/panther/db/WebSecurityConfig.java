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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/adminBase", "/admin/**").hasRole("ADMIN")
			.requestMatchers("/checkout", "/advanced", "/checkin").hasRole("USER")
				.requestMatchers("/", "/index","/static/**", "/catalog", "/css/**", "/fonts/**", "/js/**", "/register", "/search", "/catalog/**").permitAll()
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
	public InMemoryUserDetailsManager userDetailsService() throws SQLException {
		PasswordEncoder encoder =
			PasswordEncoderFactories.createDelegatingPasswordEncoder();
		JDBC SQL = new JDBC();
		String tableName = "member";
		String userID = "";
		String userPW = "";
		ResultSet rs = SQL.search(tableName);
		ResultSet admins = SQL.search("admin");
		List<String> adminIds = new ArrayList<>();
		while (admins.next()) {
			adminIds.add(admins.getString("id"));
		}
		List<UserDetails> values = new ArrayList<>();
		while (rs.next()) {
         userID = rs.getString("id");
         userPW = rs.getString("password");
		 UserDetails member;
		if (adminIds.contains(userID)) {
			member = User
				.withUsername(userID)
				.password("{bcrypt}"+userPW)
				.roles("USER","ADMIN")
				.build();
		}
		else {
			member = User
				.withUsername(userID)
				.password("{bcrypt}"+userPW)
				.roles("USER")
				.build();
		}
		 values.add(member);

        }
        rs.close();
		UserDetails[] up = new UserDetails[values.size()];
		for (int k = 0; k < values.size(); k++) {
			up[k] = values.get(k);
		}
		return new InMemoryUserDetailsManager(up);
	}
}
