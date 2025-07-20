package com.dreams.gestiontournois.configuration;

import com.dreams.gestiontournois.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final UserService userDetailsServices;
    private final PasswordEncoder passwordEncoder; //

    public SpringSecurityConfig(UserService userDetailsServices, PasswordEncoder passwordEncoder) {
        this.userDetailsServices = userDetailsServices;
        this.passwordEncoder = passwordEncoder; //
    }
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/login","/User/Registration", "/User/saveAccount").permitAll();
                    auth.requestMatchers("/Jeux/create").hasRole("ADMIN");
                    auth.requestMatchers("/Jeux/edit").hasRole("ADMIN");
                    auth.requestMatchers("/Jeux/delete").hasRole("ADMIN");
                    auth.requestMatchers("/User/ListUsers").hasRole("ADMIN");
                    auth.requestMatchers("/User/delete").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
        })
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/Home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .build();
        //                .formLogin(Customizer.withDefaults()).build();
    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER", "ADMIN").build();
//        return new InMemoryUserDetailsManager(admin);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServices);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
 }
