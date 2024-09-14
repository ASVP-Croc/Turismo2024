package com.speriamochemelacavo.turismo2024.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.speriamochemelacavo.turismo2024.services.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private MyPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersService userService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
	        .csrf(csrf -> csrf
	                .disable()
	            )
        	.authorizeHttpRequests((requests) -> requests
	                        .requestMatchers("/h2-console/**", "/", "/registration/**", "/startDbUsers", "/css/**", "/all/elements", "/search/**", "/all/users", "/error/**").permitAll()
	                        .requestMatchers("/startDbPOIs").authenticated())
					        .formLogin(form -> form
					                .loginPage("/login")
					                .permitAll()
					                .failureUrl("/login?login=false")
					            )
					        .logout(logout -> logout
					        		.logoutUrl("/logout")
					                .logoutSuccessUrl("/login?logout=true")
					                .permitAll()
			 					)
					        .userDetailsService(userService)
	                        // Permetti l'uso dei frame solo per la stessa origine
	                        .headers(headers -> headers
	                            .frameOptions(frameOptions -> frameOptions.sameOrigin())
	                        )
	                        // Disabilita CSRF per la console H2 (solo per sviluppo)
	                        ;
        return http.build();
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(passwordEncoder);
    	provider.setUserDetailsService(userService);
    	return provider;
    }
}
