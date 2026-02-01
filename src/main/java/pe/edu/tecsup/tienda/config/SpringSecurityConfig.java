package pe.edu.tecsup.tienda.config;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Algoritmo BCrypt
    }

//    @Bean
//    public UserDetailsService userDetailsServiceBean(){
//
//        List<UserDetails> users = new ArrayList<UserDetails>();
//
//        // Definimos dos usuarios en memoria
//        users.add(User.withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER").build());
//
//        users.add(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER","ADMIN").build());
//
//        return new InMemoryUserDetailsManager(users);
//
//    }

    //*
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth)
            throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    //*/

    // Autorizaciones
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de accesos
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "webjars/**", "/css/**", "/js/**", "/images/**", "/error/**").permitAll()
                        .requestMatchers("/productos/**").authenticated()
                )
                // Configuración de formulario de login
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/", true)
                )
                // Configuración de logout
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                )
                // Change csrf
                .csrf( (csrf) -> csrf.disable()
                );

        return http.build();
    }
}






















