package com.speriamochemelacavo.turismo2024.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.speriamochemelacavo.turismo2024.services.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private final AccountSecurity accountSecurity;

    public SecurityConfiguration(AccountSecurity accountSecurity) {
        this.accountSecurity = accountSecurity;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests((requests) -> requests
	                        .requestMatchers("/h2-console/**", "/", "/login", "/registration", "/startDbUsers", "/css/**", "/all/elements", "/search/**").permitAll()
	                        .requestMatchers("/logout", "/startDbPOIs").authenticated())
					        .formLogin(form -> form
					                .loginPage("/login")
					                .permitAll()
					                .defaultSuccessUrl("/", false)  // Reindirizza a una pagina dopo il login
					            )
					        .logout(logout -> logout
					                .permitAll()
					                .logoutSuccessUrl("/login?logout=true")
					            )
					        .userDetailsService(accountSecurity)
	                        // Permetti l'uso dei frame solo per la stessa origine
	                        .headers(headers -> headers
	                            .frameOptions(frameOptions -> frameOptions.sameOrigin())
	                        )
	                        // Disabilita CSRF per la console H2 (solo per sviluppo)
	                        .csrf(csrf -> csrf
	                            .ignoringRequestMatchers("/h2-console/**", "/registration")
	                        );;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(requests -> requests
//                .requestMatchers("/h2-console/**").permitAll() // Permetti l'accesso alla console H2
//                .anyRequest().authenticated()
//                .formLogin(login -> login
//                        .permitAll())
//                .logout(logout -> logout
//                        .permitAll()));
//
//        // Disabilita i frame per H2 Console
//        http.headers(headers -> headers.disable());
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    UserDetailsService userDetailsService() {
//        return username -> {
//            User user = userRepository.findByUsername(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found");
//            }
//            return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole())
//                .build();
//        };
//    }
}
