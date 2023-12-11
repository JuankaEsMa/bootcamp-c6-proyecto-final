package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.JWTAuthenticationFilter;

/**
 * @author Samson Effes
 */
@Configuration
@EnableWebSecurity
public class LibrarySecurityConfig {

    private static final String[] SECURED_URLs = {"/empleado","/empleado/**"};

    private static final String[] ALLOW_GET_URLs = {
            "/localidad",
            "/localidad/**",
            "/pais",
            "/pais/**",
            "/tematica",
            "/tematica/**",
            "/chollo",
            "/chollo/pageable",
            "/swagger-ui/**",
            "/swagger-resources/*",
            "/v3/api-docs/**",
            "/reserva",
            "/reserva/**",
            "/usuario",
            "/usuario/**",
            "/cliente",
            "/cliente/**"
    };
    private static final String[] SECURED_DELETE_URLs = {
    		"/chollo/**",
    		"/tematica/**",
    		"/localidad/**",
    		"/pais/**",
    		"/empleado/**",
    		"/cliente/**"
    };
    public static final String[] ALLOW_POST_URLs = {"/login" ,"/usuario","/reserva"};

    @Autowired
    private JWTAuthenticationFilter authenticationFilter;

    @Autowired
    private LibraryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, ALLOW_GET_URLs).permitAll()
                .requestMatchers(HttpMethod.POST,ALLOW_POST_URLs).permitAll()
                .requestMatchers(SECURED_URLs).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE,SECURED_DELETE_URLs).hasAuthority("ADMIN")
                .and().sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}