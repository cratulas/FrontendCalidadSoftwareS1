package com.example.web_seguro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    private final TokenStore tokenStore;

    public LogoutController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @GetMapping("/logout")
    public String logout() {
        tokenStore.setToken(null);
        return "redirect:/login?logout";
    }
}
