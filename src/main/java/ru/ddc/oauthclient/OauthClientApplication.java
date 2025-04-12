package ru.ddc.oauthclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthClientApplication {

    protected OauthClientApplication() {

    }

    /**
     * Main method of application.
     * @param args input arguments of application
     */
    public static void main(final String[] args) {
        SpringApplication.run(OauthClientApplication.class, args);
    }

}
