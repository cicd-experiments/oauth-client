package ru.ddc.oauthclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Test controller.
     * @return index.html
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
