package com.vincent.edmex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;

    @Autowired
    UserDetailsService service;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    @Order(1)
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        AuthenticationManagerBuilder builder = http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder())
                .and();
        return builder.build();
    }

    @Bean
    @Order(2)
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico");
    }

    @Bean
    @Order(3)
    public SecurityFilterChain filterChainHttpBasic(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/message/**").hasAnyRole("ESP", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .antMatcher("/message/**")
                .httpBasic();
        return http.build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain filterChainLoginPage(HttpSecurity http) throws Exception {
        /*
            - Richiede l'autenticazione per tutti i pattern
            - Se la richiesta coincide con ad es. /message/421 richiede l'httbasic auth, utile per mettere in sicurezza le api
            - Per tutti i restanti pattern l'autenticazione passa per la form
         */
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin((form) ->
                        form
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .permitAll())
                .logout()
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
