package com.speriamochemelacavo.turismo2024.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private MyPasswordEncoder passwordEncoder;
	
	@Autowired
	private LoggedUserDetailService loggedUserService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
	        .csrf(csrf -> csrf
	                .disable()
	            )
	        .sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        	.authorizeHttpRequests((requests) -> requests
	                        .requestMatchers("/h2-console/**", "/", "/registration/**", "/startDbUsers", "/css/**", "/all/elements", "/search/**", "/all/users", "/error/**").permitAll()
	                        .requestMatchers("/startDbPOIs").authenticated())
					        .formLogin(form -> form
					                .loginPage("/login")
					                .failureUrl("/login?login=false")
					                .permitAll()
					            )
					        .logout(logout -> logout
					        		.logoutUrl("/logout")
					                .invalidateHttpSession(true)
					                .deleteCookies("JSESSIONID")
					                .logoutSuccessUrl("/login?logout=true")
					                .permitAll()
			 					)
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
    	provider.setUserDetailsService(loggedUserService);
    	return provider;
    }
}
