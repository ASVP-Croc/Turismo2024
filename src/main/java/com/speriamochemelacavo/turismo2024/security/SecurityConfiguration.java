package com.speriamochemelacavo.turismo2024.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
	        .cors(Customizer.withDefaults())
        	.authorizeHttpRequests(requests -> requests
	                        .requestMatchers("/h2-console/**", "/", "/access/registration**", 
	                        		"/css/**", "/favicon.ico", "/element/**", "/elements/**", 
	                        		"/pois", "/tours", "/contests", 
	                        		"/startDbUsers", "/search/**", "/error/**", "/access/login**").permitAll()
	                        .requestMatchers("/access/logout/**", "/user/**", "/users/**", "/users**", "/pois/**", "/tours/**", 
	                        		"/contests/**", "/contents/**").authenticated()
							.requestMatchers("/startDbPOIs", "/startDbTours", "/creation", 
									"/pois/creation", "/tours/creation", 
									"/pois/add", "/tours/add", "/contents/add").hasAnyRole("CONTRIBUTOR", "AUTHORIZED_CONTRIBUTOR", "CURATOR", "ANIMATOR", "ADMINISTRATOR")
							.requestMatchers("/contests/creation", "/contests/add").hasAnyRole("ANIMATOR", "ADMINISTRATOR")
							.requestMatchers("/validations", "/validation").hasAnyRole("CURATOR","ADMINISTRATOR")
							.requestMatchers("/validations", "/validation").hasAnyRole("ANIMATOR","ADMINISTRATOR")
							.requestMatchers("/user/list", "/users").hasRole("ADMINISTRATOR")
        		)
					        .formLogin(form -> form
					                .loginPage("/access/login")
					                .failureUrl("/access/login?login=false")
					                .permitAll()
					            )
					        .logout(logout -> logout
					        		.logoutUrl("/access/logout")
					                .logoutSuccessUrl("/access/login?logout=true")
					                .invalidateHttpSession(true)
					                .deleteCookies("JSESSIONID")
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
