package com.example.HappyNewHere.config;

import com.example.HappyNewHere.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {
    private final AccountService accountService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                                authorize
                                        .requestMatchers("/login/kakao","/index.html").permitAll()
                                        .requestMatchers("/test").hasAuthority("USER")
                                        .requestMatchers("**").permitAll()
//                                .requestMatchers("/user/signin","user/signup").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/**").authenticated()
//                                .requestMatchers(HttpMethod.GET, "/**").authenticated()
//                                .requestMatchers(HttpMethod.PUT, "/**").authenticated()
//                                .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                )
                .addFilterBefore(new JwtFilterConfig(accountService,secretKey), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}