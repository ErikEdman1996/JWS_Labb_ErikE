package org.example.jws_labb_erike.configs;

import org.example.jws_labb_erike.utils.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/api/v2/count").hasRole("admin")
                                .anyRequest().authenticated()
                )
                //Krävs för att se h2-console
                .headers(headers->headers.frameOptions(frame->frame.sameOrigin()))
                .oauth2ResourceServer(oauth2->oauth2.
                        jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );

        return http.build();
    }
}
