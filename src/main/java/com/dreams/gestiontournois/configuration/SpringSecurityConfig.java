package com.dreams.gestiontournois.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth ->{
            auth.requestMatchers("/DoubleElimination/admin").hasRole("ADMIN");
            auth.requestMatchers("/DoubleElimination/user").hasRole("USER");
            auth.requestMatchers("/DoubleElimination/ListeTournois").permitAll();
            auth.requestMatchers("/DoubleElimination/view/{id}").permitAll();
            auth.requestMatchers("/DoubleElimination/createTournois").permitAll();
            auth.requestMatchers("/DoubleElimination/save").permitAll();
            auth.requestMatchers("/DoubleElimination/editTournoi/{id}").permitAll();
            auth.requestMatchers("/DoubleElimination/delete/{id}").permitAll();
            auth.anyRequest().authenticated();
        }).formLogin(form -> form
                        .loginPage("/Connection/login")
                        .defaultSuccessUrl("/Accueil", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/Connection/login?logout")
                        .permitAll()
                )
                .build();


        //                .formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 }
