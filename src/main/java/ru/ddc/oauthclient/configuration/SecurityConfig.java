package ru.ddc.oauthclient.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * Security configuration class.
     * @param http HttpSecurity object for configuration.
     * @return SecurityFilterChain object
     * @throws Exception when something goes wrong
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {

        http.oauth2Client(Customizer.withDefaults());

        http.oauth2Login(Customizer.withDefaults());

        http.logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "AUTH_SESSION_ID", "KC_RESTART",
                        "KEYCLOAK_IDENTITY", "KEYCLOAK_SESSION"));


        http.authorizeHttpRequests(ex -> ex
                .requestMatchers("/", "/index.html", "/favicon.ico").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}
