package org.example.jws_labb_erike.configs;

import org.example.jws_labb_erike.utils.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{

    private final JwtAuthConverter jwtAuthConverter;

    @Autowired
    public SecurityConfig(final JwtAuthConverter jwtAuthConverter)
    {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->
                        auth
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2->oauth2.
                        jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );

        return http.build();
    }
}
