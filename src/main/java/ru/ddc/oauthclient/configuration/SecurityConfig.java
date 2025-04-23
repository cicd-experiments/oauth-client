package ru.ddc.oauthclient.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ClientRegistrationRepository clientRegistrationRepository;
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

        http.logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()));

        http.authorizeHttpRequests(ex -> ex
                .requestMatchers("/", "/index.html", "/favicon.ico").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);

        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("https://dipdeepcode.ru");

        return oidcLogoutSuccessHandler;
    }
}
