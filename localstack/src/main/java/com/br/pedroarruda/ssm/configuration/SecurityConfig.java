package com.br.pedroarruda.ssm.configuration;

import com.br.pedroarruda.ssm.repository.UsersRepository;
import com.br.pedroarruda.ssm.security.CustomUserDetailService;
import com.br.pedroarruda.ssm.security.LoginSocialSucessHandler;
import com.br.pedroarruda.ssm.services.LoginServices;
import com.br.pedroarruda.ssm.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import java.lang.annotation.Annotation;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSucessHandler sucessHandler) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(configurer -> {
                configurer.loginPage("/login/Telalogin").permitAll()
                .loginProcessingUrl("/login/Telalogin")
                .defaultSuccessUrl("/TelaMenu");

            })
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/login/**").permitAll();
                authorize.requestMatchers("/cadastroGasto/**").hasAnyRole("ADMIN", "FUNCIONARIO", "GERENTE");
                authorize.requestMatchers("/user/**").hasAnyRole("ADMIN", "GERENTE");
                authorize.requestMatchers("/TelaMenu").hasAnyRole("ADMIN", "FUNCIONARIO", "GERENTE");
            })
            .oauth2Login(oauth2 -> {
                oauth2
                        .loginPage("/login/Telalogin")
                        .successHandler(sucessHandler);
            })
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    //@Bean
    public UserDetailsService userDetailsService(UsersRepository usersRepository){
            return new CustomUserDetailService(usersRepository);
    }

    //@Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) throws Exception {

        return new ProviderManager(
                new DaoAuthenticationProvider() {{
                    setUserDetailsService(userDetailsService);
                    setPasswordEncoder(passwordEncoder);
                }}
        );
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}


