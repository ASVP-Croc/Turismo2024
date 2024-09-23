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
import com.speriamochemelacavo.turismo2024.models.users.Role;

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
        	.authorizeHttpRequests(requests -> requests
	                        .requestMatchers("/h2-console/**", "/", "/registration/**", 
	                        		"/css/**", "/favicon.ico", "/element/**", "/elements/**", 
	                        		"/pois", "/tours", "/contests",
	                        		"/startDbUsers", "/search/**", "/error/**").permitAll()
	                        .requestMatchers("/pois/**", "/tours/**", 
	                        		"/contests/**", "/contents/**").hasAnyRole("AUTHENTICATED_TOURIST", "CONTRIBUTOR", "AUTHORIZED_CONTRIBUTOR", "CURATOR", "ANIMATOR", "ADMINISTRATOR")
	                        .requestMatchers("/users/all").hasRole("ADMINISTRATOR")
							.requestMatchers("/startDbPOIs", "/startDbTours", "/creation", 
									"/pois/creation", "/tours/creation", 
									"/pois/add", "/tours/add", "/contents/add").hasAnyRole("CONTRIBUTOR", "AUTHORIZED_CONTRIBUTOR", "CURATOR", "ANIMATOR", "ADMINISTRATOR")
							.requestMatchers("/contests/creation", "/contests/add").hasAnyRole("ANIMATOR", "ADMINISTRATOR")
							.requestMatchers("/validations", "/validation").hasAnyRole("CURATOR","ADMINISTRATOR")
							.requestMatchers("/users/**").hasRole("ADMINISTRATOR")
        		)
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
	                        .headers(headers -> headers
	                            .frameOptions(frameOptions -> frameOptions.sameOrigin())
	                        )
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
